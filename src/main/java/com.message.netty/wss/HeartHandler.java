package com.message.netty.wss;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("进入读空闲模式");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("进入写空闲模式");
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println("进入读写全部模式");
                System.out.println("连接的数量为" + ChatHandler.users.size());
                Channel channel = ctx.channel();
                channel.close();
                System.out.println("连接的数量为" + ChatHandler.users.size());
            }
        }
    }
}
