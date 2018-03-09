package com.easymvc.aop.proxy;


/**
 * @author softwareluke
 * @date 2018-03-6 23:50:59
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}