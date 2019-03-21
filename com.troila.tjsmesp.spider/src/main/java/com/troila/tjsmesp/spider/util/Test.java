package com.troila.tjsmesp.spider.util;

import java.io.RandomAccessFile;

class Example{
    String str;
    public Example(){
        str= "example";
    }
    public Example(String s){
        str=s;
    }
}
class Demo extends Example{
}
public class Test{
    public void f (){
        Example ex = new Example("Good");
//        Demo d = new Demo("Good");
        Demo d = new Demo();
    } 
    
    int[] ia = new int[1];
    boolean b;
    int i;
    Object o;
//    public void print() {
//    	System.out.println(ia*0+" "+b+" "+o);
//    }
    public static void main(String[] args) {
//		Object obj = new Object();
//		Thread t1 = new Thread();
//		
//		int[] a = new int[]{1,2,3,4,5};
		
		
//		MyClass a;
//		MySubClass b;
//		
//		a = new MyClass();
//		a = new MySubClass();
//		a = b;
//		b = a;
//		
//		a = new MySubClass();
//		b = new MyClass();
    	
    	/*int data_arr[]={12,31,56,23,27,1,43,65,4,99};
    	try {
    		RandomAccessFile randf = new RandomAccessFile("temp.dat", "rw");
    		for(int i=0;i<data_arr.length;i++) {
    			randf.writeInt(data_arr[i]);   			
    		}
    		for(int i=data_arr.length-1;i>=0;i--){
    			randf.seek(i*4);
    			System.out.println(randf.readInt());
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	Thread t1 = new Thread();
    	Double d1 = new Double(1.0);
	}*/
    	
    short b = 11;
    long  c = 11;
    System.out.println(b==c);
}
}

//class MyClass{
//	
//}
//class MySubClass extends MyClass{
//	
//}