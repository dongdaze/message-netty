package com.message.netty.wss;

import com.message.netty.util.JsonUtils;
import com.message.netty.vo.ChatMsg;
import com.message.netty.vo.DataContent;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext context, TextWebSocketFrame msg) throws Exception {
        String content = msg.text();
        System.out.println("客户端发送来的消息为:" + content);
        // 获取当前连接的channel
        Channel currentChannel = context.channel();

        DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
        Integer action = dataContent.getAction();
        ChatMsg chatMsg = dataContent.getChatMsg();


    }

    /**
     * 当客户端建立连接后，获取客户端client，放到clients中去
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    /**
     * 当客户端失去连接，默认会移除掉该连接
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开，对应的长ID为: " + ctx.channel().id().asLongText());
        System.out.println("客户端断开，对应的短ID为: " + ctx.channel().id().asShortText());
        users.remove(ctx.channel());
    }
}
