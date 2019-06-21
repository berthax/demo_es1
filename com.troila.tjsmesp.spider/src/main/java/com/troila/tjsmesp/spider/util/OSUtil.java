package com.troila.tjsmesp.spider.util;

/**
 * 判断当前操作系统类型工具类
 * @author xuanguojing
 *
 */
public class OSUtil {
	
	private static final boolean osMac;
    private static final boolean osWindows;
    private static final boolean osLinux;
    
    static {   	
        String os = System.getProperty("os.name");
        if(os != null)
            os = os.toLowerCase();        
        osLinux = os.indexOf("linux")>=0;
        osWindows = os.indexOf("windows")>=0;
        osMac = os.indexOf("mac")>=0;
    }

	public static boolean isOsmac() {
		return osMac;		
	}

	public static boolean isOswindows() {
		return osWindows;
	}

	public static boolean isOslinux() {
		return osLinux;
	}
		
/*	public static void main(String[] args) {
		System.out.println("osMac:"+osMac);
		System.out.println("osWindows:"+osWindows);
		System.out.println("osLinux:"+osLinux);
		String str1 = "1111";
		List<String> list1 = new ArrayList<>();
		list1.sort(comparing(String::length));
		list1.stream().forEach(System.out::println);
	}*/
}
