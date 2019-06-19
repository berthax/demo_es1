package com.troila.tjsmesp.spider.repository.informix;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.troila.tjsmesp.spider.model.secondary.BmsPlatformPublishInfo;

public interface BmsPlatformPublishInfoRepositoryInformix extends JpaRepository<BmsPlatformPublishInfo, Integer>{
	
	/**
	 * 根据原文链接查找对应的政策原文记录
	 * @param fromLink
	 * @return
	 */
	public List<BmsPlatformPublishInfo> findByOrgLink(String orgLink);
	
}
