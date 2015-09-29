/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package com.yueqiu.mis.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yueqiu.core.entity.Activity;
import com.yueqiu.core.entity.Order;
import com.yueqiu.core.entity.Stadium;
import com.yueqiu.core.entity.User;
import com.yueqiu.core.model.ActivityStatus;
import com.yueqiu.core.model.OrderStatus;
import com.yueqiu.core.utils.Constants;

/**
 * description here
 *
 * @author yezi
 * @since 2015年9月26日
 */
@Controller
public class YueQiuController extends BaseController {

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public String location(@RequestParam(required = false) String q, @RequestParam(required = false) String cid,
            Model model) {
        model.addAttribute("q", q);
        model.addAttribute("cid", StringUtils.defaultIfBlank(cid, "010"));
        return vm("location");
    }

    @RequestMapping(value = "/stadiums", method = RequestMethod.GET)
    public String stadiumsGet(Model model) {
        List<Stadium> list = stadiumService.listAll();
        model.addAttribute("stadiums", list);
        return vm("stadium/stadiumlist");
    }

    @RequestMapping(value = "/stadiums/{id}", method = RequestMethod.GET)
    public String stadiumGet(@PathVariable String id, Model model) {
        if (!id.equals("0")) {
            Stadium stadium = stadiumService.get(id);
            model.addAttribute("stadium", stadium);
        }
        model.addAttribute("id", id);
        return vm("stadium/stadium");
    }

    @RequestMapping(value = "/stadiums/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> stadiumPost(@ModelAttribute Stadium stadium, @PathVariable String id, Model model) {
        ModelAndView mv = new ModelAndView();
        if (!id.equals("0")) {
            Stadium old = stadiumService.get(id);
            if (old != null) {
                old.setAddress(stadium.getAddress());
                old.setGallery(stadium.getGallery());
                old.setLatitude(stadium.getLatitude());
                old.setLongitude(stadium.getLongitude());
                old.setName(stadium.getName());
                old.setPhone(stadium.getPhone());
                old.setSize(stadium.getSize());
                stadiumService.update(old);
            } else {
                stadiumService.create(stadium);
            }
        } else {
            stadiumService.create(stadium);
        }
        logger.info("update or create {}", stadium);

        mv.addObject("code", 200);
        mv.addObject("msg", "ok");
        return mv.getModel();
    }

    @RequestMapping(value = "/activities", method = RequestMethod.GET)
    public String activities(Model model, @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit) {
        List<Activity> list = activityService.listAll(ActivityStatus.ALL, offset, limit);
        model.addAttribute("activities", list);
        model.addAttribute("offset", offset);
        model.addAttribute("limit", limit);
        return vm("activity/activitylist");
    }

    @RequestMapping(value = "/activities/{id}", method = RequestMethod.GET)
    public String activityGet(@PathVariable String id, Model model) {
        if (!id.equals("0")) {
            Activity activity = activityService.get(id);
            model.addAttribute("activity", activity);
        }
        model.addAttribute("stadiums", stadiumService.listAll());
        model.addAttribute("id", id);
        return vm("activity/activity");
    }

    @RequestMapping(value = "/activities/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> activityPost(@PathVariable String id, @RequestParam String title,
            @RequestParam String stadiumId, @RequestParam String dateStr, @RequestParam float duration,
            @RequestParam float price, @RequestParam int total, @RequestParam int status, Model model) {
        ModelAndView mv = new ModelAndView();
        Activity activity;
        if (id.equals("0")) {
            activity = new Activity();
        } else {
            activity = activityService.get(id);
        }
        activity.setTitle(title);
        Stadium stadium = stadiumService.get(stadiumId);
        if (stadium == null) {
            mv.addObject("code", 400);
            mv.addObject("msg", "球场获取失败");
            return mv.getModel();
        }
        activity.setStadium(stadium);
        try {
            activity.setDate(DateUtils.parseDate(dateStr, Constants.MIS_ACT_DATE_FORMAT));
        } catch (ParseException e) {
            logger.error("{}", e.getMessage(), e);
        }
        activity.setDuration(duration);
        activity.setPrice(price);
        activity.setTotal(total);
        activity.setStatus(status);
        activityService.upsert(activity);
        logger.info("update or create {}", activity);

        mv.addObject("code", 200);
        mv.addObject("msg", "ok");
        return mv.getModel();
    }

    @RequestMapping(value = "/activities/{id}", method = RequestMethod.DELETE)
    public String activityDelete(@PathVariable String id, Model model) {
        return vm("activity/activity");
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(@RequestParam(required = false) String mobile, @RequestParam(defaultValue = "0") int offset,
            Model model) {
        if (StringUtils.isNotBlank(mobile)) {
            User user = userService.getByMobile(mobile);
            model.addAttribute("user", user);
            List<Order> orders = orderService.listByUser(user, OrderStatus.ALL, offset, 10);
            model.addAttribute("orders", orders);
            model.addAttribute("offset", offset);
        }
        return vm("user/usersearch");
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST)
    public String userPost(@PathVariable String id, Model model) {
        return vm("user/user");
    }
    
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(@RequestParam(required = false) String id, Model model) {
        if (StringUtils.isNotBlank(id)) {
            Order order = orderService.get(id);
            model.addAttribute("order", order);
        }
        return vm("order/ordersearch");
    }
    
    @RequestMapping(value = "/orders/activity/{id}", method = RequestMethod.GET)
    public String orderActivity(@PathVariable String id, Model model) {
        Activity activity = activityService.get(id);
        List<Order> orders = orderService.listByActivity(activity, OrderStatus.ALL);
        model.addAttribute("activity", activity);
        model.addAttribute("orders", orders);
        return "order/orderlist";
    }
}
