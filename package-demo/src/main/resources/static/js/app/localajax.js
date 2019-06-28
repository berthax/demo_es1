 /* $.ajax({
        	url: "/testRead",
        	type: "post",
        	dataType: "text",
        	processData:false, //不要将发送的数据转换成字符串
        	data: zsbuf,
//        	dataFilter:function(data, type){
//        		// 字符串转为ArrayBuffer对象，参数为字符串
//        		
//        		    
//              	 
//              	 
//        	},
        	success: function (data) {  
        		var buf = new ArrayBuffer(data.length); // 每个字符占用2个字节
    		    var bufView = new Uint8Array(buf);
    		    for (var i=0, strLen=data.length; i<strLen; i++) {
    		         bufView[i] = data.charCodeAt(i);
    		    }
    		    console.log(bufView)
          	 var objBuffer = new flatbuffers.ByteBuffer(bufView);
          	 var p = com.hx.cloud.Person.getRootAsPerson(objBuffer);
          
	           	 var name = p.name();
	           	 console.log(p)
	           	 console.log(name)
	           	 console.log(p.age())
        	 },
        	error: function (data) {
                //200的响应也有可能被认定为error，responseText中没有Message部分
                 alert($.parseJSON(data.responseText).Message);
             },
             complete: function (data) {
                 ;//after success or error
            },
            beforeSend:function(xhr,options){
//                jqXHR.setRequestHeader("custom-header", "custom-info") ;  // 增加一个自定义请求头
            	xhr.setRequestHeader("Content-Type","application/x-flatbuffer");
                xhr.setRequestHeader("Accept", "application/x-flatbuffer");
                xhr.overrideMimeType("text/plain; charset=x-user-defined");
              }
        });*/

         
         /**
	 * byte数组转换为整型
	 * @param b
	 * @return
	 */
	var byteArray2Int = function(src) {
//        var value = 0;
//        for (var i = b.length-1; i >=0; i--) {
//            var shift = (b.length-1 - i) * 8;
//            value += (b[i] & 0x000000FF) << shift;
//        }       
//        return value;
        	
        var value;    
        value = ((src[0] & 0xFF)   
            | ((src[1] & 0xFF)<<8)   
            | ((src[2] & 0xFF)<<16)   
            | ((src[3] & 0xFF)<<24));  
        return value;  
        		
    }
