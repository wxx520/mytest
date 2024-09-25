package com.wxxtest.rpc.framework.constant.enums;

public enum TaskStatusEnum {

	CREATED(2),
	ONGOING(0),
	TERMINATED(6),
	COMPLETED(5),
	REMIND(1),
	HANG(4),
	TO_BE_SUMMARIZED(3);

	public static boolean isFinished(int taskStatus) {
		return TaskStatusEnum.TERMINATED.ordinal() == taskStatus
						|| TaskStatusEnum.COMPLETED.ordinal() == taskStatus;
	}

	public static TaskStatusEnum parse(int ordinal) {
		return values()[ordinal];
	}

	private final int displayOrder;

	TaskStatusEnum(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}
}
