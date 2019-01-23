package com.troila.tjsmesp.spider.util;  
  
import java.sql.Types;

import org.hibernate.dialect.InformixDialect;  
  
/**
 * 
 * @author xuanguojing
 * 此类暂时不需要了
 *
 */
public class Informix10DialectEx extends InformixDialect {  
  
/** 
* 添加BLOB与CLOB类型的映射 
*/  
public Informix10DialectEx() {  
//        super();  
//        registerColumnType(Types.BLOB, "blob" );  
        registerColumnType(Types.CLOB, "text" );  
}   
 
//informix低版本不需要这个skip分页
//informix10支持使用skip m first n进行分页  
//    public boolean useMaxForLimit() {  
//        return false;  
//    }  
//  
//    public boolean supportsLimitOffset() {  
//        return true;  
//    }  
//      
//    public String getLimitString(String querySelect, int offset, int limit) {  
//        String os = "";  
//        if (offset > 0) os = " skip "  + offset;          
//        return new StringBuffer( querySelect.length() + 24 + os.length())  
//            .append(querySelect)  
//            .insert( querySelect.toLowerCase().indexOf( "select" ) + 6,  
//                    " {+ FIRST_ROWS }" + os + " first " + limit )  
//            .toString();  
//    }  
}  