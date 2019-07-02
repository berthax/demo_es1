package com.troila.tjsmesp.spider.service.impl;

import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

public class LocalBeanScanner extends ClassPathBeanDefinitionScanner{

	public LocalBeanScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		// TODO Auto-generated method stub
		return super.doScan(basePackages);
	}

	
}
