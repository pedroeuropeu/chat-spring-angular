package com.chat.exceptions;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.LinkedHashMap;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.chat.model.ErrorResponse;

@ControllerAdvice
public class ChatExceptionControllerAdvice implements ResponseBodyAdvice<Object>{
	
	private static final Logger log = LoggerFactory.getLogger(ChatExceptionControllerAdvice.class);
    
    @ResponseBody
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Error.class)
	public ErrorResponse ExceptionHandler(Error e) {
         return new ErrorResponse(e.getLocalizedMessage());
	}
    
	@ResponseBody
	@ResponseStatus(FORBIDDEN)
	@ExceptionHandler(ServletException.class)
	public ErrorResponse ExceptionHandler(ServletException e) {
		return ErrorResponse.getErrorResponse(e);
	}
	
	@ResponseBody
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorResponse ExceptionHandler(Exception e) {
		return ErrorResponse.getErrorResponse(e);
	}
	
	@ResponseBody
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrorResponse ExceptionHandler(HttpMessageNotReadableException e) {
		return ErrorResponse.getErrorResponse(e);
	}
    
    @ExceptionHandler(ChatException.class)
    @ResponseBody
    public ErrorResponse handleException(ChatException ex, Model model) {
        return ErrorResponse.getErrorResponse(ex);
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		try {
			LinkedHashMap<String, String> objMap = (LinkedHashMap<String, String>) body;
			if(objMap.get("error").equals("Unauthorized") || objMap.get("error").equals("Forbidden")) {
				body = new ErrorResponse("User Unauthorized");
			}else if(objMap.get("error").equals("Not Found")){
				body = new ErrorResponse("Page not found");
			}else if(objMap.get("error").equals("Internal Server Error")){
				body = new ErrorResponse("Internal Server Error","500");
			}
			return body;
		} catch (Exception e) {
			return body;
		}
		
	}

    @Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}
}