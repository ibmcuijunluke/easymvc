package com.easymvc.aop.manager;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.List;

import com.easymvc.aop.proxy.Proxy;
import com.easymvc.aop.proxy.ProxyChain;


/**
 * @author softwareluke
 * @date 2018-03-6 23:50:59
 */
public class ProxyManager {
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList){
        return (T) Enhancer.create(targetClass,
                (MethodInterceptor) (targetObject, targetMethod, methodParams, methodProxy) ->
                        new ProxyChain(targetClass, targetObject, targetMethod, methodProxy, methodParams, proxyList));
    }
}