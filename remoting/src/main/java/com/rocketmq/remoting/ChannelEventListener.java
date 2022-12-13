package com.rocketmq.remoting;

import io.netty.channel.Channel;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-11  17:44
 * @Version: 1.0
 */
public interface ChannelEventListener {

    void onChannelConnect(final String remoteAddr, final Channel channel);

    void onChannelClose(final String remoteAddr, final Channel channel);

    void onChannelException(final String remoteAddr, final Channel channel);

    void onChannelIdle(final String remoteAddr, final Channel channel);

}
