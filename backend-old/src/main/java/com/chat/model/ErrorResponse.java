package com.chat.model;

import com.chat.exceptions.ChatException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private String msgError;
	private String codError;

	public ErrorResponse(String msgError) {
		this.msgError = msgError;
	}

	public ErrorResponse() {
		super();
	}
	
	public static ErrorResponse getErrorResponse(ChatException ex) {
		ErrorResponse rj = new ErrorResponse();
		rj.setCodError(ex.getCodMessage());
		rj.setMsgError(ex.getLocalizedMessage());
		return rj;
	}


	public static ErrorResponse getErrorResponse(Exception ex) {
		return getErrorResponse(new ChatException(ex.getMessage(),ex));
	}

}