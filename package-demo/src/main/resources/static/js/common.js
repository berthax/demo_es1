require.config({
	baseUrl : "js",
	map: { //map告诉RequireJS在任何模块之前，都先载入这个css模块
        '*': {
            css: 'libs/css.min'
        }
    },
    paths:{
    	person:"fbjs/Person_generated",
    	jquery:"libs/jquery-1.11.3.min",
    	flatbuffer:"libs/flatbuffers",
    	ajaxsender:"libs/ajaxsender"
    },
    shim:{
    	jquery:{
			exports : "$"
		},
		ajaxsender:{
			exports:"ajaxsender"
		}
    }
})