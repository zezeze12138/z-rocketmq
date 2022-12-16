package com.rocketmq.remoting.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.FileRegion;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-11  18:20
 * @Version: 1.0
 */
public class FileRegionEncoder extends MessageToByteEncoder<FileRegion> {

    protected void encode(ChannelHandlerContext ctx, FileRegion msg, final ByteBuf out) throws Exception {
        WritableByteChannel writableByteChannel = new WritableByteChannel() {
            public int write(ByteBuffer src) throws IOException {
                out.writeBytes(src);
                return out.capacity();
            }

            public boolean isOpen() {
                return true;
            }

            public void close() throws IOException {

            }
        };

        long toTransfer = msg.count();

        while (true){
            long transferred = msg.transferred();
            if(toTransfer - transferred <= 0){
                break;
            }
            msg.transferTo(writableByteChannel, transferred);
        }
    }
}
