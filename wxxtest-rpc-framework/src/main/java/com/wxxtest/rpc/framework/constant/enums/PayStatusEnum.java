package com.wxxtest.rpc.framework.constant.enums;

import java.util.List;

public enum PayStatusEnum {

    /**
     * ("本地订单已创建，外部(如: 支付宝)订单可能还未创建")
     */
    LOCAL_ORDER_CREATED(List.of(1, 2, 3, 4)),
    /**
     * ("本地订单创建超过5分钟状态未变更，置为超时；外部(如: 支付宝)订单可能还未创建")
     */
    LOCAL_ORDER_TIMEOUT(List.of(2, 3, 4)),
    /**
     * ("外部订单已创建，等待用户支付或获取支付结果中")
     */
    WAITING(List.of(3, 4)),
    /**
     * ("外部订单支付超时，被取消")
     */
    CANCELED(List.of()),
    /**
     * ("外部订单支付成功")
     */
    SUCCESS(List.of(5)),
    /**
     * ("外部订单支付成功后，又发生退款")
     */
    REFUNDED(List.of());

    private final List<Integer> canTransferTo;
    PayStatusEnum(List<Integer> canTransferTo) {
        this.canTransferTo = canTransferTo;
    }

    public static boolean canTransferTo(PayStatusEnum from, PayStatusEnum to) {
        return from.canTransferTo.contains(to.ordinal());
    }

    public static List<String> ongoingStatusName() {
        return List.of(LOCAL_ORDER_CREATED.name(), WAITING.name());
    }

    public static PayStatusEnum fromAlipayPcOrderStatusQuery(String status, PayStatusEnum currentStatus) {
        switch (status) {
            case "WAIT_BUYER_PAY" -> { return WAITING; }
            case "TRADE_CLOSED" -> {
                if (currentStatus == SUCCESS) {
                    return REFUNDED;
                } else {
                    return CANCELED;
                }
            }
            case "TRADE_SUCCESS", "TRADE_FINISHED" -> { return SUCCESS; }
            default -> throw new IllegalStateException("unexpected alipay pc order status: " + status);
        }
    }
}
