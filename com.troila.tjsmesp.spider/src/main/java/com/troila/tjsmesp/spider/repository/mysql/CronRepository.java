package com.troila.tjsmesp.spider.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.troila.tjsmesp.spider.model.primary.Cron;

public interface CronRepository extends JpaRepository<Cron, Integer>{

}
