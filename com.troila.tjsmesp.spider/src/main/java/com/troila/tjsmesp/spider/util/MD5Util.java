package com.troila.tjsmesp.spider.util;

import java.io.IOException;

import org.springframework.util.DigestUtils;  
  
public class MD5Util {  
      
    //用于混交md5
    private static final String slat = "&%5123***&&%%$$#@";
    /**
     * 生成md5
     * @param seckillId
     * @return
     */
    public static String getMD5(String str) {
        String base = str +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public static void main(String[] args) throws IOException {  
        String s1 = "1123455554654d6f54a65sdfjsoejioaiejksdflkjaldfkjs;lakdfjla";
        String md5Str1 = getMD5(s1);
        System.out.println(md5Str1+"    长度为："+md5Str1.length());
        
    }  
}  
