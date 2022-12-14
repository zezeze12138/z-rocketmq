package com.rocketmq.remoting.protocol;

import io.netty.buffer.ByteBuf;

/**
 * @Author: zengqz
 * @Description: TODO
 * @CreateTime: 2022-12-11  18:35
 * @Version: 1.0
 */
public class RemotingCommand {

    private static SerializeType serializeTypeConfigInThisServer = SerializeType.JSON;

    private transient byte[] body;

    private SerializeType serializeTypeCurrentRPC = serializeTypeConfigInThisServer;

    public void fastEncodeHeader(ByteBuf out){
        int bodySize = this.body != null ? this.body.length : 0;
        int beginIndex = out.writerIndex();

        out.writeLong(0);
        int headerSize;
        if(SerializeType.ROCKETMQ == serializeTypeCurrentRPC){

        }else{
            //this.makeCustomHeaderToNet();
        }
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
