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
	 * windows下的chrome浏览器driver
	 */
	private String chromeWindowsDriver;
	/**
	 * linux下的chrome浏览器driver
	 */
	private String chromeLinuxDriver;
	/**
	 * max下的chrome历览器driver
	 */
	private String chromeMacDriver;

	public String getChromeWindowsDriver() {
		return chromeWindowsDriver;
	}

	public void setChromeWindowsDriver(String chromeWindowsDriver) {
		this.chromeWindowsDriver = chromeWindowsDriver;
	}

	public String getChromeLinuxDriver() {
		return chromeLinuxDriver;
	}

	public void setChromeLinuxDriver(String chromeLinuxDriver) {
		this.chromeLinuxDriver = chromeLinuxDriver;
	}

	public String getChromeMacDriver() {
		return chromeMacDriver;
	}

	public void setChromeMacDriver(String chromeMacDriver) {
		this.chromeMacDriver = chromeMacDriver;
	}

}
