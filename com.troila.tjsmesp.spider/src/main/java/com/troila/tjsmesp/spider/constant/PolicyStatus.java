package com.troila.tjsmesp.spider.constant;
/**
 * 政策状态
 * @author xuanguojing
 *
 */
public final class PolicyStatus {
	/**
	 * 待审核
	 */
	public static final int Pending = 0;
	/**
	 * 审核通过
	 */
	public static final int Approved = 1;
	/**
	 * 拒绝
	 */
	public static final int Rejected = 2;
	/**
	 * 撤销
	 */
	public static final int Canceled = 3;
	/**
	 * 草稿
	 */
	public static final int Draft = 99;

	private PolicyStatus() {
	}
}
