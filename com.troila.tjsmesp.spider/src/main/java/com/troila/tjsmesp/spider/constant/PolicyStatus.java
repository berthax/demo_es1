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
	 * 审核通过，已发布
	 */
	public static final int Approved = 1;
	/**
	 * 已拒绝
	 */
	public static final int Rejected = 2;
	/**
	 * 已撤销
	 */
	public static final int Canceled = 3;
	/**
	 * 草稿，即待提交状态
	 */
	public static final int Draft = 99;

	private PolicyStatus() {
	}
}
