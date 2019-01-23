package com.troila.tjsmesp.spider.repository.informix;

import org.springframework.data.jpa.repository.JpaRepository;

import com.troila.tjsmesp.spider.model.secondary.SmePolicy;

public interface SmePolicyRespositoryInformix extends JpaRepository<SmePolicy, Integer>{
	/**
	 * 根据原文链接查找对应的政策原文记录
	 * @param fromLink
	 * @return
	 */
	public SmePolicy findByFromLink(String fromLink);
}
