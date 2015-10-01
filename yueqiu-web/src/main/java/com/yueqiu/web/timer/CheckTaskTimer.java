/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.web.timer;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yueqiu.core.entity.CheckTask;
import com.yueqiu.core.service.CheckTaskService;

/**
 * description here
 *
 * @author yezi
 * @since 2015年10月1日
 */
@Component
public class CheckTaskTimer implements InitializingBean {

    @Autowired
    CheckTaskService checkTaskService;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        List<CheckTask> tasks = checkTaskService.listAll();
        for (CheckTask checkTask : tasks) {
            checkTaskService.execute(checkTask.getCheckType(), checkTask.getCheckId());
        }
    }

}
