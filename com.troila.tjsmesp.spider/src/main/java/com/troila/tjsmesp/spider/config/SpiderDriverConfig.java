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
	 * 使用的浏览器类型chromedriver/geckodriver，其他类型暂不支持
	 */
	private String type;
	/**
	 * windows下的浏览器驱动
	 */
	private String windowsDriver;
	/**
	 * linux下的浏览器驱动
	 */
	private String linuxDriver;
	/**
	 * max下的浏览器驱动
	 */
	private String macDriver;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
	@Override
	public String toString() {
		return "SpiderDriverConfig [type=" + type + ", windowsDriver=" + windowsDriver + ", linuxDriver=" + linuxDriver
				+ ", macDriver=" + macDriver + "]";
	}
}
