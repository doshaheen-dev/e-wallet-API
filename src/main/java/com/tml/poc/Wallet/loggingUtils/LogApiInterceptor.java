package com.tml.poc.Wallet.loggingUtils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tml.poc.Wallet.repository.CustomTraceRepository;
import com.tml.poc.Wallet.services.ResquestRespLogMongoservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.lang.StringBuffer;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.Collections;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class LogApiInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(LogApiInterceptor.class);

	private static String headerStr="";
	
	
	@Autowired
	ResquestRespLogMongoservice requestLogService;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (response instanceof ResettableStreamHttpServletResponse) {
			((ResettableStreamHttpServletResponse) response).payloadFilePrefix = ((ResettableStreamHttpServletRequest) request).payloadFilePrefix;
			((ResettableStreamHttpServletResponse) response).payloadTarget = ((ResettableStreamHttpServletRequest) request).payloadTarget;
			writeResponsePayloadAudit((ResettableStreamHttpServletResponse) response);
		}
	}

	public String getRawHeaders(HttpServletRequest request) {
		StringBuffer rawHeaders = new StringBuffer();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			rawHeaders.append(key).append(":").append(value).append("\n");
		}

		return rawHeaders.toString();
	}
	
	
	public String getRawHeadersRequestId(HttpServletRequest request) {
		StringBuffer rawHeaders = new StringBuffer();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
//			rawHeaders.append(key).append(":").append(value).append("\n");
			if(key.equalsIgnoreCase("RequestId")) {
				return value;
			}
		}

		return null;
	}

	public String getRawHeadersRespId(HttpServletResponse response) {
		StringBuffer rawHeaders = new StringBuffer();
		Enumeration headerNames = Collections.enumeration(response.getHeaderNames());
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = response.getHeader(key);
//			rawHeaders.append(key).append(":").append(value).append("\n");
			if(key.equalsIgnoreCase("RequestId")) {
				return value;
			}
		}

		return null;
	}

	public String getRawHeaders(HttpServletResponse response) {
		StringBuffer rawHeaders = new StringBuffer();
		Enumeration headerNames = Collections.enumeration(response.getHeaderNames());
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = response.getHeader(key);
			rawHeaders.append(key).append(":").append(value).append("\n");
		}

		return rawHeaders.toString();
	}

	private void writePayloadAudit(String payloadFile, String rawHeaders, String requestBody) throws IOException {
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(payloadFile), StandardCharsets.UTF_8))) {
			writer.write(rawHeaders);
			writer.write("\n");
			writer.write(requestBody);
		}
	}

	
	/**
	 * request payload writtern and taking logs
	 * @param wrappedRequest
	 */
	public void writeRequestPayloadAudit(ResettableStreamHttpServletRequest wrappedRequest) {
		try {
			String requestHeaders = getRawHeaders(wrappedRequest);
			String requestId = getRawHeadersRequestId(wrappedRequest);
			String requestBody = org.apache.commons.io.IOUtils.toString(wrappedRequest.getReader());
			logger.info("Request Method: " + wrappedRequest.getMethod());
			logger.info("Request Headers:");
			logger.info(requestHeaders);
			logger.info("Request body:");
			logger.info(requestBody);
			
//			 String url = ((HttpServletRequest)request).getRequestURL().toString();
//			 String queryString = ((HttpServletRequest)request).getQueryString();
			requestLogService.addRequestIntoLogMongo(requestId,wrappedRequest.getRequestURI(), requestHeaders, requestBody);
			
		} catch (Exception e) {
//	    	logger.error(e.getMessage());
		}
	}

	public void writeResponsePayloadAudit(ResettableStreamHttpServletResponse wrappedResponse) {
		try {	
			String rawHeaders = getRawHeaders(wrappedResponse);
			String requestId = getRawHeadersRespId(wrappedResponse);

			logger.info("Response Status: " + wrappedResponse.getStatus());
			logger.info("Response Headers:");
			logger.info(rawHeaders);
			logger.info("Response body:");
			byte[] data = new byte[wrappedResponse.rawData.size()];
			for (int i = 0; i < data.length; i++) {
				data[i] = (byte) wrappedResponse.rawData.get(i);
			}
			String responseBody = new String(data);
			logger.info(responseBody);
			
			requestLogService.addResponseIntoLogMongo(requestId, rawHeaders, responseBody);

		} catch (Exception e) {
			// TODO: handle exception
		}
		// String requestBody = org.apache.commons.io.IOUtils.toString(obj.getReader());
		// writePayloadAudit(payloadFile, rawHeaders, requestBody);
	}
}
