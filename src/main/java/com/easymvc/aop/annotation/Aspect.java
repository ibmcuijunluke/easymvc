package com.easymvc.aop.annotation;

import java.lang.annotation.*;


/**
 * @author softwareluke
 * @date 2018-03-6 23:50:59
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * Value class.
     *
     * @return the class
     */
    Class<? extends Annotation> value();
}
