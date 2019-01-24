package com.troila.tjsmesp.spider.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * Chrome浏览器各个系统下的driver，
 * ChromeDriver v2.43 (2018-10-16)，支持 Chrome v69-71，
 * @author xuanguojing
 *
 */
@Component
@ConfigurationProperties(prefix="spider.driver")
public class SpiderDriverConfig {
	/**
	 * windows下的firefox浏览器driver
	 */
	private String windowsDriver;
	/**
	 * linux下的firefox浏览器driver
	 */
	private String linuxDriver;
	/**
	 * max下的firefox浏览器driver
	 */
	private String macDriver;
	public String getWindowsDriver() {
		return windowsDriver;
	}
	public void setWindowsDriver(String windowsDriver) {
		this.windowsDriver = windowsDriver;
	}
	public String getLinuxDriver() {
		return linuxDriver;
	}
	public void setLinuxDriver(String linuxDriver) {
		this.linuxDriver = linuxDriver;
	}
	public String getMacDriver() {
		return macDriver;
	}
	public void setMacDriver(String macDriver) {
		this.macDriver = macDriver;
	}
}
