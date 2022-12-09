package com.rocketmq.nameserv.config;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-06  23:31
 * @Version: 1.0
 */
public class NettyServerConfig implements Cloneable {

    private int listenPort = 8888;
    private int serverWorkerThreads = 8;
    private int serverCallbackExecutorThreads = 0;
    private int serverSelectorThreads = 3;
    private int serverOnewaySemphoreValue = 256;
    private int serverAsyncSemaphoreValue = 64;
    private int serverChannelMaxIdleTimeSeconds = 120;



}
