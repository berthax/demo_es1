package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Policy;

@Component
public interface PolicyRepository extends ElasticsearchRepository<Policy, String> {

	Optional<Policy> findById(String id);

	/**
	 * 查询标题是title的记录
	 * 
	 * @param title
	 * @return
	 */
	List<Policy> findByTitle(String title);

	/**
	 * 查询标题不是title的记录
	 * 
	 * @param tile
	 * @return
	 */
	List<Policy> findByTitleNot(String tile);
}
