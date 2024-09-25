/*
 * Copyright (c) Jaguar Land Rover Ltd 2024. All rights reserved
 */

package com.wxxtest.rpc.registration.center.client.rpc.framework.registry.v3.msg;

import io.netty.buffer.ByteBuf;

import java.util.concurrent.CompletableFuture;

public class MsgWrapper<T> {

    /**
     * a Future used to get the corresponding ack message
     */
    private CompletableFuture<T> ackFuture;

    private ByteBuf msg;

    private long requestId;

    public CompletableFuture<T> getAckFuture() {
        return ackFuture;
    }

    public void setAckFuture(CompletableFuture<T> ackFuture) {
        this.ackFuture = ackFuture;
    }

    public ByteBuf getMsg() {
        return msg;
    }

    public void setMsg(ByteBuf msg) {
        this.msg = msg;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }
}
