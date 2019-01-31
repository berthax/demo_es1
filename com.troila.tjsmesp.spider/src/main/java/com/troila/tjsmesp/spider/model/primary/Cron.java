package com.troila.tjsmesp.spider.model.primary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cron")
public class Cron {
	@Id
    @GeneratedValue
	private Integer id;
	
	
	private String cronId;
	
	
	private String cron;

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCronId() {
		return cronId;
	}


	public void setCronId(String cronId) {
		this.cronId = cronId;
	}


	public String getCron() {
		return cron;
	}


	public void setCron(String cron) {
		this.cron = cron;
	}
	
	
}
