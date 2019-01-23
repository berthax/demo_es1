package com.troila.tjsmesp.spider.repository.informix;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.troila.tjsmesp.spider.model.PolicySpider;

public interface PolicySpiderRepositoryInformix extends JpaRepository<PolicySpider, String>{
	
	public Optional<PolicySpider> findById(String id);
	
	public List<PolicySpider> findByStripContentContains(String str);
}
