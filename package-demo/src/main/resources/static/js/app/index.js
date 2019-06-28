require(["../common"],function(){
	require(["person","jquery","flatbuffer","ajaxsender"],function(person,$,flatbuffer,ajaxsender){
		
		var builder = new flatbuffers.Builder(0);
		var zsName = builder.createString("张三");
		com.hx.cloud.Person.startPerson(builder);
		com.hx.cloud.Person.addName(builder,zsName);
		com.hx.cloud.Person.addAge(builder,22);
		com.hx.cloud.Person.addCode(builder,1);
		var zs = com.hx.cloud.Person.endPerson(builder);
		builder.finish(zs);
		var zsbuf = builder.asUint8Array();
		console.log(zsbuf);
				
		/* console.log(com)
		 var namespace = "com.hx.cloud";
		
		 var xhr = new XMLHttpRequest();
		 xhr.open("post","testRead");
		 xhr.setRequestHeader("Content-Type","application/x-flatbuffer");
         xhr.setRequestHeader("Accept", "application/x-flatbuffer");
         if ("overrideMimeType" in xhr){			 
             xhr.overrideMimeType("text/plain; charset=x-user-defined");
          }
         xhr.responseType = 'arraybuffer';
         xhr.onreadystatechange = function fetchOnReadyStateChange() {
        	 if (xhr.readyState !== 4){
                 return undefined;
             }
        	 var buffer = xhr.response;
        	 console.log(buffer)
        	 var dataResp = new Uint8Array(buffer);
        	 console.log(dataResp)
        	 
        	 var buf = new flatbuffers.ByteBuffer(dataResp);          	 
        	 var objType = xhr.getResponseHeader("X-Flatbuffer-Schema");
        	 if(!objType){
        		 return undefined;
        	 }
        	 var constructor =  namespace + "."+objType + "." + "getRootAs" + objType;
        	 eval( "var _function = " + constructor);
        	 var entity = _function(buf);        	 
        	 console.log(entity)
        	 console.log(entity.name())
        	 console.log(entity.age())
        	 console.log(entity.code())
         }
         xhr.send(zsbuf)*/
		var ajax = ajaxsender({
			url:"testRead",
			data:zsbuf,
			success:function(data){
				console.log(data.name())
	        	console.log(data.age())
	        	console.log(data.code())
			},
			fail:function(){
				
			}
		})
		ajax.send("testRead",zsbuf);
		
	})
})