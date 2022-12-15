package com.rocketmq.remoting.netty;

import com.rocketmq.remoting.common.RemotingUtil;
import com.rocketmq.remoting.protocol.RemotingCommand;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-11  18:33
 * @Version: 1.0
 */
@Slf4j
@ChannelHandler.Sharable
public class NettyEncoder extends MessageToByteEncoder<RemotingCommand> {
    protected void encode(ChannelHandlerContext ctx, RemotingCommand remotingCommand, ByteBuf out) throws Exception {
        try{
            remotingCommand.fastEncodeHeader(out);
            byte[] body = remotingCommand.getBody();
            if(body != null){
                out.writeBytes(body);
            }
        }catch (Exception e){

            if(remotingCommand != null){

            }
            RemotingUtil.closeChannel(ctx.channel());
        }
    }
}
