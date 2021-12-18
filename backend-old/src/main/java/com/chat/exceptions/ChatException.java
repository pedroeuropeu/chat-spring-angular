package com.chat.exceptions;

public class ChatException extends Exception{

    private static final long serialVersionUID = 1L;
    String codMessage = "NOT_APPLICABLE";
    
    public ChatException(String msg){
        super(msg);
    }
    
    public ChatException(String msg, String codMessage){
        super(msg);
        this.codMessage = codMessage;
    }

    public ChatException(String msg, Throwable cause){
        super(msg, cause);
    }
    
    public String getCodMessage() {
        return codMessage;
    }

    public void setCodMessage(String codMessage) {
        this.codMessage = codMessage;
    }
    
}