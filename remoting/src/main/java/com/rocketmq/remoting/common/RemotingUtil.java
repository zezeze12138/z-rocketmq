package com.rocketmq.remoting.common;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-11  22:30
 * @Version: 1.0
 */
public class RemotingUtil {
    public static void closeChannel(Channel channel) {
        final String addrRemote = RemotingHelper.parseChannelRemoteAddr(channel);
        channel.close().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                        future.isSuccess());
            }
        });
    }
}
