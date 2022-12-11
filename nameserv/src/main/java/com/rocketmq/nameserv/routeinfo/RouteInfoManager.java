package com.rocketmq.nameserv.routeinfo;

import com.rocketmq.common.protocol.route.BrokerData;
import com.rocketmq.common.protocol.route.QueueData;
import com.rocketmq.common.protocol.topic.TopicList;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: zengqz
 * @Description: 路由信息管理
 * @CreateTime: 2022-12-11  13:14
 * @Version: 1.0
 */
@Slf4j
public class RouteInfoManager {

    private final static long BROKER_CHANNEL_EXPIRED_TIME = 1000 * 60 * 2;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    //Map<topic, Map<brokerName, QueueData>>
    private final HashMap<String, Map<String, QueueData>> topicQueueTable;
    //Map<brokerName, BrokerData>
    private final HashMap<String, BrokerData> brokerAddrTable;
    //Map<clusterName, brokerName>
    private final HashMap<String, Set<String>> clusterAddrTable;

    public RouteInfoManager(HashMap<String, Set<String>> clusterAddrTable) {
        this.topicQueueTable = new HashMap<>(1024);
        this.brokerAddrTable = new HashMap<>(128);
        this.clusterAddrTable = new HashMap<>(32);
    }

    public void deleteTopic(final String topic){
        try{
            try{
                this.lock.writeLock().lockInterruptibly();
                //主题队列表中移除主题
                this.topicQueueTable.remove(topic);
            }finally {
                this.lock.writeLock().unlock();
            }
        }catch (Exception e){
            log.error("获取所有主题列表异常");
        }
    }

    public TopicList getAllTopicList(){
        TopicList topicList = new TopicList();
        try{
            try{
                this.lock.readLock().lockInterruptibly();
                topicList.getTopicList().addAll(this.topicQueueTable.keySet());
            }finally {
                this.lock.readLock().unlock();
            }
        }catch (Exception e){
            log.error("获取所有主题列表异常");
        }
        return topicList;
    }

}
