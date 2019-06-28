/*;(function(undefined){
	var namespace = "com.hx.cloud";
	// 当请求成功时，将arraybuffer结果处理成flatbuffer类
	var converterResult = function(data,objType){
		 var dataArray = new Uint8Array(data);
 		 var buffer = new flatbuffers.ByteBuffer(dataArray);
 		 var constructorStr =  namespace + "." + objType + "." + "getRootAs" + objType;
 		 eval( "var _function = " + constructor);
 		 var result = _function(buf);
 		 console.log(result)
 		 console.log(result.name())
 		 console.log(result.age())	
 		 return result;
	}
	// 处理get请求的参数，对其编码
	var param = function(data){
		var arr = [];
		for(var param in arr){
			arr.push(encodeURIComponent(param) + '=' +encodeURIComponent(data[param]));
		}
		arr.push('randomNumber=' + Math.random);
		arr.join('&');
		return arr;
	}
	var ajaxsender = function(options){		
		this.options = options || {};  
		// 初始化数据
		this.options.data = options.data || {};
		// 初始化请求方式，默认是为get请求
		this.options.type = (options.type || "POST").toUpperCase();
		// 初始化数据类型，默认为arraybuffer
		this.options.dataType = options.dataType || "arraybuffer";
		// 初始化同步或异步请求，默认为异步
		this.options.async = options.async || true;
		// 初始化一些请求头的设置
		this.options.contentType = options.contentType || "application/x-flatbuffer";
		this.options.accept = options.accept || "application/x-flatbuffer";
		this.options.responseType = options.responseType || "arraybuffer";
		// 初始化成功或失败的请求
		this.options.success = {};
		this.options.fail = {};
		this.xhr = null;		
		// 获取XMLHttpRequest，兼容IE7以下的版本
		this.xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");		
		if(!this.xhr){
			console.log("Your browser does not support XMLHTTP.")
			return null;
		}
		return this;
	}
	
	ajaxsender.prototype.send = function(url,data){
		var xhr = this.xhr;
		var options = this.options;
		//设置header之前要先打开xhr
		xhr.open(options.type,url,options.async);
		xhr.setRequestHeader("Content-Type",options.contentType);
		xhr.setRequestHeader("Accept", options.accept);
		xhr.responseType =options.responseType;
		if ("overrideMimeType" in xhr){			 
			xhr.overrideMimeType("text/plain; charset=x-user-defined");
		}	
		
		xhr.onreadystatechange = function fetchOnReadyStateChange() {			
	       	 if (xhr.readyState !== 4){
	       		options.fail(xhr, xhr.statusText, xhr.status);
	         }
	       	 if((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304){
	       		 var objType = xhr.getResponseHeader("X-Flatbuffer-Schema");
	       		 var data = xhr.response;
	       		 if(!objType ||options.responseType != "arraybuffer"){
	       			 // 不需要处理的类型，直接返回
	       			 options.success(data);
	       		 }	       		 
//	       		 console.log(data)
//	       		 var dataArray = new Uint8Array(data);
//	       		 console.log(dataResp)
//	       		 var buffer = new flatbuffers.ByteBuffer(dataArray);
//	       		 var constructorStr =  namespace + "."+objType + "." + "getRootAs" + objType;
//	       		 eval( "var _function = " + constructor);
//	       		 var result = _function(buf);
	       		 var resultObj = converterResult(data,objType);
	       		 options.success(resultObj);
	       	 }else{
	       		 options.fail(xhr, xhr.statusText, xhr.status);
	       	 }
        }
		if(options.type == 'GET'){
			xhr.open('GET',options.url+'?'+params(options.data),options.async);
			xhr.send(null);
		}else{
			xhr.open(options.type,options.url,options.async);
			xhr.send(options.data);
		}
		xhr.send(data);
	}
	
	_global = (function () {
        return this || (0, eval)('this')
    }());

    if (typeof module !== 'undefined' && module.exports) {
        module.exports = ajaxsender
    } else if (typeof define == 'function' && define.amd) {
        define(function () {
            return ajaxsender
        })
    } else {
        !('ajaxsender' in _global) && (_global.ajaxsender = ajaxsender)
    }
}())*/

define(["jquery"],function($){
	var namespace = "com.hx.cloud";
	// 当请求成功时，将arraybuffer结果处理成flatbuffer类
	var converterResult = function(data,objType){
		 var dataArray = new Uint8Array(data);
 		 var buffer = new flatbuffers.ByteBuffer(dataArray);
 		 var constructorStr =  namespace + "." + objType + "." + "getRootAs" + objType;
 		 eval( "var _function = " + constructorStr);
 		 var result = _function(buffer);
 		 console.log(result)
 		 console.log(result.name())
 		 console.log(result.age())	
 		 return result;
	}
	// 处理get请求的参数，对其编码
	var param = function(data){
		var arr = [];
		for(var param in arr){
			arr.push(encodeURIComponent(param) + '=' +encodeURIComponent(data[param]));
		}
		arr.push('randomNumber=' + Math.random);
		arr.join('&');
		return arr;
	}
	var ajaxsender = function(options){		
		this.options = options || {};  
		// 初始化数据
		this.options.data = options.data || {};
		// 初始化请求方式，默认是为get请求
		this.options.type = (options.type || "POST").toUpperCase();
		// 初始化数据类型，默认为arraybuffer
		this.options.dataType = options.dataType || "arraybuffer";
		// 初始化同步或异步请求，默认为异步
		this.options.async = options.async || true;
		// 初始化一些请求头的设置
		this.options.contentType = options.contentType || "application/x-flatbuffer";
		this.options.accept = options.accept || "application/x-flatbuffer";
		this.options.responseType = options.responseType || "arraybuffer";
		// 初始化成功或失败的请求
		this.options.success = options.success || function(){};
		this.options.fail = options.fail || function(){};
		this.xhr = null;		
		// 获取XMLHttpRequest，兼容IE7以下的版本
		this.xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");		
		if(!this.xhr){
			console.log("Your browser does not support XMLHTTP.")
			return null;
		}
		return this;
	}
	
	ajaxsender.prototype.send = function(url,data){
		var xhr = this.xhr;
		var options = this.options;
		//设置header之前要先打开xhr
		xhr.open(options.type,url,options.async);
		xhr.setRequestHeader("Content-Type",options.contentType);
		xhr.setRequestHeader("Accept", options.accept);
		xhr.responseType =options.responseType;
		if ("overrideMimeType" in xhr){			 
			xhr.overrideMimeType("text/plain; charset=x-user-defined");
		}	
		
		xhr.onreadystatechange = function fetchOnReadyStateChange() {			
	       	 if (xhr.readyState !== 4){
	       		options.fail(xhr, xhr.statusText, xhr.status);
	       		return;
	         }
	       	 if((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304){
	       		 var objType = xhr.getResponseHeader("X-Flatbuffer-Schema");
	       		 var data = xhr.response;
	       		 if(!objType ||options.responseType != "arraybuffer"){
	       			 // 不需要处理的类型，直接返回
	       			 options.success(data);
	       		 }	       		 
	       		 var resultObj = converterResult(data,objType);
	       		 options.success(resultObj);
	       	 }else{
	       		 options.fail(xhr, xhr.statusText, xhr.status);
	       	 }
        }
		xhr.send(data);
	}
	
	/*_global = (function () {
        return this || (0, eval)('this')
    }());

    if (typeof module !== 'undefined' && module.exports) {
        module.exports = ajaxsender
    } else if (typeof define == 'function' && define.amd) {
        define(function () {
            return ajaxsender
        })
    } else {
        !('ajaxsender' in _global) && (_global.ajaxsender = ajaxsender)
    }*/
	
	//this is same to four line code upper//跟上面四行一样
	return {
		ajaxsender:ajaxsender,
//		"version":"1.0.0",
//		loadTip:loadTip
	};
	
	
});