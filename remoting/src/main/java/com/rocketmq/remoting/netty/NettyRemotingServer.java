package com.rocketmq.remoting.netty;

import com.rocketmq.remoting.ChannelEventListener;
import com.rocketmq.remoting.RemotingService;
import com.rocketmq.remoting.common.TlsMode;
import com.rocketmq.remoting.protocol.RemotingCommand;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zengqz
 * @Description: Netty远程服务
 * @CreateTime: 2022-12-11  17:35
 * @Version: 1.0
 */
@Slf4j
public class NettyRemotingServer implements RemotingService{

    private final ServerBootstrap serverBootstrap;
    private final EventLoopGroup eventLoopGroupSelector;
    private final EventLoopGroup eventLoopGroupBoss;
    private final NettyServerConfig nettryServerConfig;

    private final ChannelEventListener channelEventListener;

    private DefaultEventExecutorGroup defaultEventExecutorGroup;

    private static final String HANDSHAKE_HANDLER_NAME = "handshakeHandler";
    private static final String TLS_HANDLER_NAME = "sslHandler";
    private static final String FILE_REGION_ENCODER_NAME = "fileRegionEncoder";

    protected volatile SslContext sslContext;

    private HandshakeHandler handshakeHandler;
    private NettyEncoder encoder;
    private NettyConnectManageHandler connectionManageHandler;
    private NettyServerHandler serverHandler;

    public NettyRemotingServer(final NettyServerConfig nettryServerConfig, final ChannelEventListener channelEventListener) {

        this.serverBootstrap = new ServerBootstrap();
        this.nettryServerConfig = nettryServerConfig;
        this.channelEventListener = channelEventListener;

        int publicThreadNums = nettryServerConfig.getServerCallbackExecutorThreads();
        if(publicThreadNums <= 0){
            publicThreadNums = 4;
        }

        this.eventLoopGroupBoss = new NioEventLoopGroup(1, new ThreadFactory() {
            private AtomicInteger threadIndex = new AtomicInteger(0);

            public Thread newThread(Runnable r) {
                return new Thread(r, String.format("NettyNIOBoos_%d", this.threadIndex.incrementAndGet()));
            }
        });

        this.eventLoopGroupSelector = new NioEventLoopGroup(nettryServerConfig.getServerSelectorThreads(), new ThreadFactory() {
            private AtomicInteger threadIndex = new AtomicInteger(0);
            private int threadTotal = nettryServerConfig.getServerSelectorThreads();

            public Thread newThread(Runnable r) {
                return new Thread(r, String.format("NettyServerNIOSelector_%d_%d", threadTotal, this.threadIndex.incrementAndGet()));
            }
        });

    }


}
