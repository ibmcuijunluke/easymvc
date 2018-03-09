package com.easymvc.web;

import java.util.Arrays;

import com.easymvc.aop.helper.AopHelper;
import com.easymvc.web.bean.BeanContainer;
import com.easymvc.web.helper.ClassHelper;
import com.easymvc.web.helper.ControllerHelper;
import com.easymvc.web.helper.IocHelper;
import com.easymvc.web.utils.ClassUtil;

/**
 * 初始化框架
 * @author chengfan
 */
public class Loader {

    public static void init() {
        Class<?>[] cs = {ClassHelper.class,
                BeanContainer.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class};

        Arrays.stream(cs)
                .forEach(c -> ClassUtil.loadClass(c.getName(),true));

    }
}
