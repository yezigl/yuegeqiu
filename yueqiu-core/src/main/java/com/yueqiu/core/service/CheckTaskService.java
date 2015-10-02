/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.core.service;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Service;

import com.yueqiu.core.entity.Activity;
import com.yueqiu.core.entity.CheckTask;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.model.ActivityStatus;
import com.yueqiu.core.model.CheckType;
import com.yueqiu.core.model.OrderStatus;
import com.yueqiu.core.utils.Constants;

/**
 * description here
 *
 * @author yezi
 * @since 2015年10月1日
 */
@Service
public class CheckTaskService extends BaseService {

    private Timer timer = new Timer("CheckTaskTimer", true);

    private Map<ObjectId, TimerTask> taskMap = new ConcurrentHashMap<>();

    public List<CheckTask> listAll() {
        Query<CheckTask> query = checkTaskDao.createQuery();
        return query.asList();
    }

    public void submit(CheckType checkType, ObjectId checkId) {
        CheckTask checkTask = new CheckTask();
        checkTask.setCheckType(checkType);
        checkTask.setCheckId(checkId);
        Query<CheckTask> query = checkTaskDao.createQuery();
        query.field("checkId").equal(checkId);
        if (!checkTaskDao.exists(query)) {
            checkTaskDao.save(checkTask);
            
            execute(checkType, checkId);
        }
    }

    public void execute(CheckType checkType, ObjectId checkId) {
        switch (checkType) {
        case ORDER_PAY:
            Order order = orderDao.get(checkId);
            if (order != null) {
                OrderPayTask task = new OrderPayTask(order);
                long delay = Constants.ORDER_EXPIRE_TIME
                        - (System.currentTimeMillis() - order.getCreateTime().getTime());
                if (delay > 0) {
                    timer.schedule(task, delay);
                    taskMap.put(order.getId(), task);
                } else {
                    task.run();
                }
                logger.info("check order task {} schedule, delay = {}", order.getId(), delay);
            } else {
                logger.warn("check order task is null, id = {}", checkId);
            }
            break;
        case ACTIVITY_STATUS:
            Activity activity = activityDao.get(checkId);
            if (activity != null) {
                ActivityStatusTask task = new ActivityStatusTask(activity);
                long delay = activity.getDate().getTime() - System.currentTimeMillis();
                if (delay > 0) {
                    timer.schedule(task, delay);
                    taskMap.put(activity.getId(), task);
                } else {
                    task.run();
                }
                logger.info("check activity task {} schedule, delay = {}", activity.getId(), delay);
            } else {
                logger.warn("check activity task is null, id = {}", checkId);
            }
            break;
        }
    }

    public void cancel(ObjectId id) {
        TimerTask task = taskMap.get(id);
        if (task != null) {
            task.cancel();
            deleteTask(id);
            logger.info("check task {} cancel", id);
        }
    }

    class OrderPayTask extends TimerTask {

        Order order;

        public OrderPayTask(Order order) {
            this.order = order;
        }

        @Override
        public void run() {
            order.setStatus(OrderStatus.CLOSE.code);
            orderDao.update(order);
            deleteTask(order.getId());
            logger.info("check order task {} execute", order.getId());
        }

    }

    class ActivityStatusTask extends TimerTask {

        Activity activity;

        public ActivityStatusTask(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void run() {
            activity.setStatus(ActivityStatus.FINISH.status);
            activityDao.update(activity);
            deleteTask(activity.getId());
            logger.info("check activity task {} execute", activity.getId());
        }

    }

    private void deleteTask(ObjectId id) {
        // 删除checktask
        Query<CheckTask> query = checkTaskDao.createQuery();
        query.field("checkId").equal(id);
        checkTaskDao.deleteByQuery(query);
    }
}
