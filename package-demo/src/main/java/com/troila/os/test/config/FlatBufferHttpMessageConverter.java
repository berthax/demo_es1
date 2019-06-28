package com.troila.os.test.config;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.google.flatbuffers.Table;

public class FlatBufferHttpMessageConverter extends AbstractHttpMessageConverter<Table>{

	//定义默认编码类型
	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	
	public static final MediaType FLATBUF_JSON = new MediaType("application", "x-flatbuffer", DEFAULT_CHARSET);
	
	public static final String X_FLATBUF_SCHEMA_HEADER = "X-Flatbuffer-Schema";
	
	public static final String X_FLATBUF_MESSAGE_HEADER = "X-Flatbuffer-Message";
	
	public static final Map<Class<? extends Table>,Method> methodCache = new ConcurrentHashMap<>();
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	
	@Override
	protected boolean canRead(MediaType mediaType) {
		return FLATBUF_JSON.equals(mediaType);
	}


	@Override
	protected boolean canWrite(MediaType mediaType) {
		return super.canWrite(mediaType) || mediaType.equals(FLATBUF_JSON) ;
	}


	public FlatBufferHttpMessageConverter() {
		super();
		this.setSupportedMediaTypes(Arrays.asList(new MediaType[]{FLATBUF_JSON}));
	}


	@Override
	protected Table readInternal(Class<? extends Table> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		
		InputStream is = inputMessage.getBody();		
		int count = 0;         
        count = is.available();  
        byte[] inArray = new byte[count];  
        is.read(inArray);  
          
		ByteBuffer byteBuffer = ByteBuffer.allocate(inArray.length);
		byteBuffer.clear();
	    byteBuffer.put(inArray, 0, inArray.length);
	    byteBuffer.flip();
	    
	    Method cache = methodCache.get(clazz);
	    if(cache == null) {
	    	//解析出二进制为具体的FlatBuffer对象。
	    	Method[] methods = clazz.getDeclaredMethods();		
	    	for(Method method : methods) {
	        	if(!method.getName().contains("getRootAs")) {
	        		continue;
	        	}
	        	Type[] types = method.getGenericParameterTypes();
	        	if(types.length != 1) {
	        		continue;       	
	        	}
	        	methodCache.put(clazz, method);
	        	cache = method;
	    	}
	    }
		try {
			return (Table) cache.invoke(null, byteBuffer);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		} 	    
	}


	@Override
	protected void writeInternal(Table t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		ByteBuffer buffer = t.getByteBuffer();
		int len = buffer.capacity() - buffer.position();
		byte[] result = new byte[len];
		buffer.get(result, 0, len);
		outputMessage.getHeaders().add(X_FLATBUF_SCHEMA_HEADER, t.getClass().getSimpleName());
		outputMessage.getBody().write(result);
	}

}
