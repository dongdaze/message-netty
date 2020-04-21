package com.message.netty.wss;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.time.LocalDateTime;

public class WSServer {

    private EventLoopGroup mainGroup;

    private EventLoopGroup subGroup;

    private ServerBootstrap server;

    private ChannelFuture future;

    public static class SingleWSServer {
        final static WSServer INSTANCE = new WSServer();
    }

    public static WSServer getInstance() {
        return SingleWSServer.INSTANCE;
    }

    public WSServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup).channel(NioServerSocketChannel.class)
                .childHandler(new WSSeverInitializer());
    }

    public void start() {
        this.future = this.server.bind(8088);
        System.err.println(LocalDateTime.now() + "  INFO " + Thread.currentThread() + "netty started........");
    }


}
