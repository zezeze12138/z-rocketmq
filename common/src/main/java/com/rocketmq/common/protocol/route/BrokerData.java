package com.rocketmq.common.protocol.route;

import com.rocketmq.common.MixAll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-11  13:43
 * @Version: 1.0
 */
public class BrokerData implements Comparable<BrokerData>{

    private String cluster;
    private String brokerName;
    private HashMap<Long, String> brokerAddrs;

    private final Random random = new Random();

    public BrokerData() {
    }

    public BrokerData(String cluster, String brokerName, HashMap<Long, String> brokerAddrs) {
        this.cluster = cluster;
        this.brokerName = brokerName;
        this.brokerAddrs = brokerAddrs;
    }


    /**
     * 选择Broker地址，如果master也就是brokerAddrs的key为0的没有的话，就在slave中随机选取一个
     * @return broker地址
     */
    public String selectBrokerAddr(){
        String addr = this.brokerAddrs.get(MixAll.MASTER_ID);
        if(addr == null){
            List<String> addrs = new ArrayList<>(brokerAddrs.values());
            return addrs.get(random.nextInt(addrs.size()));
        }
        return addr;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public HashMap<Long, String> getBrokerAddrs() {
        return brokerAddrs;
    }

    public void setBrokerAddrs(HashMap<Long, String> brokerAddrs) {
        this.brokerAddrs = brokerAddrs;
    }

    public int compareTo(BrokerData o) {
        return this.brokerName.compareTo(o.getBrokerName());
    }
}
