package com.rocketmq.remoting.common;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.net.SocketAddress;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-11  22:31
 * @Version: 1.0
 */
public class RemotingHelper {

    private static final AttributeKey<String> REMOTE_ADDR_KEY = AttributeKey.valueOf("RemoteAddr");

    public static String parseChannelRemoteAddr(Channel channel) {
        if(null == channel){
            return "";
        }
        Attribute<String> att = channel.attr(REMOTE_ADDR_KEY);
        if(att == null){
            return parseChannelRemoteAddr0(channel);
        }
        String addr = att.get();
        if (addr == null) {
            addr = parseChannelRemoteAddr0(channel);
            att.set(addr);
        }
        return addr;
    }

    private static String parseChannelRemoteAddr0(Channel channel) {
        SocketAddress remote = channel.remoteAddress();
        final String addr = remote != null ? remote.toString() : "";

        if(addr.length() > 0){
            int index = addr.lastIndexOf("/");
            if(index >= 0){
                return addr.substring(index + 1);
            }
            return addr;
        }
        return "";
    }
}
