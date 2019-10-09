package com.demo.netty.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel


class NettyServer {

    companion object {

        /**
         * 主入口函数
         * @JvmStatic 只有加了这个注解我们才可以实现类似Java的静态方法的效果
         */
        @JvmStatic
        fun main(args: Array<String>) {
            NettyServer().bing(7397)
        }

    }

    private fun bing(port: Int) {
        //配置服务端NIO线程组
        var parentGroup = NioEventLoopGroup()
        var childGroup = NioEventLoopGroup()
        var b = ServerBootstrap()
        b.group(parentGroup, childGroup)
            .channel(
                NioServerSocketChannel::class.java
            )    //非阻塞模式
            .option(ChannelOption.SO_BACKLOG, 128)
            .childHandler(MyChannelInitializer())
        var f = b.bind(port).sync()
        f.channel().closeFuture().sync()
    }

}