package com.easymvc.aop.proxy;

import java.lang.reflect.Method;


/**
 * @author softwareluke
 * @date 2018-03-6 23:50:59
 */
public abstract class AbstractAspectProxy implements Proxy{

    @Override
    public final Object doProxy(ProxyChain proxyChain) throws Throwable{
        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if (intercept(cls, method, params)) {
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            error(cls, method, params, e);
            throw e;
        } finally {
            end();
        }

        return result;
    }

    private void before(Class<?> cls, Method method, Object[] params) throws Throwable {
    }
    private void after(Class<?> cls, Method method, Object[] params) throws Throwable {
    }
    private void error(Class<?> cls, Method method, Object[] params, Throwable throwable) {
    }

    public void begin(){

    }

    public void end(){

    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable{
        return true;
    }
}