package com.kkyeer.study.dubboconsumer;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.List;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in  2020-3-13 11:00
 * @Modified By:
 */
public class AlwaysFirstLoadBalancer implements LoadBalance {
    public AlwaysFirstLoadBalancer() {
        System.out.println("init");
    }

    /**
     * select one invoker in list.
     *
     * @param invokers   invokers.
     * @param url        refer url
     * @param invocation invocation.
     * @return selected invoker.
     */
    @Override
    public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        Invoker<T> maxWeightInvoker = null;
        int maxWeight = 0;
        for (Invoker<T> invoker : invokers) {
            URL invokerUrl = invoker.getUrl();
            int weight = invokerUrl.getParameter("weight", 1);
            System.out.println("weight:" + weight + ",url:" + invokerUrl.getHost());
            if (weight > maxWeight) {
                maxWeightInvoker = invoker;
            }
        }
        return maxWeightInvoker;
    }
}