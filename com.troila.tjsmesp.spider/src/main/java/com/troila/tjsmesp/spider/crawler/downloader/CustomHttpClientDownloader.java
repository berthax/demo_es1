package com.troila.tjsmesp.spider.crawler.downloader;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

public class CustomHttpClientDownloader extends HttpClientDownloader{
	private static final Logger logger = LoggerFactory.getLogger(CustomHttpClientDownloader.class);

	@Override
	protected Page handleResponse(Request request, String charset, HttpResponse httpResponse, Task task) throws IOException {
	    // 调用父类处理响应
	    Page page = super.handleResponse(request, charset, httpResponse, task);
	    int code = page.getStatusCode();
	    logger.info("页面响应url为【{}】,返回的状态码为【{}】",request.getUrl(),code);
	    // 状态码判断
	    if (HttpStatus.SC_OK <= code && code < HttpStatus.SC_INTERNAL_SERVER_ERROR) {
	    	return page;
	    } else {
	    	logger.warn("下载[{}]错误, 响应码: {}, 不在给定的范围内[{}-{})", request.getUrl(), code, HttpStatus.SC_OK, HttpStatus.SC_INTERNAL_SERVER_ERROR);
	        page.setDownloadSuccess(false);
	    }
	    return page;
	}
}
