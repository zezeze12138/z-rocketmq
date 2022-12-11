package com.rocketmq.common.protocol.topic;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zengqz
 * @Description: 主题列表
 * @CreateTime: 2022-12-11  15:47
 * @Version: 1.0
 */
public class TopicList {

    private Set<String> topicList = new HashSet<>();
    private String brokerAddr;

    public Set<String> getTopicList() {
        return topicList;
    }

    public void setTopicList(Set<String> topicList) {
        this.topicList = topicList;
    }

    public String getBrokerAddr() {
        return brokerAddr;
    }

    public void setBrokerAddr(String brokerAddr) {
        this.brokerAddr = brokerAddr;
    }
}
