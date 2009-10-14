package com.rusticisoftware.cheddargetter.client;

public class CGException extends Exception {
	public static final int REQUEST_INVALID = 400;
	public static final int PRECONDITION_FAILED = 412;
	public static final int NOT_FOUND = 404;
	public static final int DATA_INVALID = 500;
	public static final int USAGE_INVALID = 500;
	public static final int UNKNOWN = 500;
	
	private int code = UNKNOWN;
	public int getCode(){
		return code;
	}
	public void setCode(int code){
		this.code = code;
	}
	
	public CGException (int code, String message){
		super(message);
		this.setCode(code);
	}
	
	public String toString(){
		return "CGException: Code = " + getCode() + " Message = " + this.getMessage();
	}
	
	public String getParsedMessage(){
		try {
			String entireMessage = this.getMessage();
			int startIndex = this.getMessage().lastIndexOf("=>");
			String parsedMessage = entireMessage.substring(startIndex+2);
			int endIndex = parsedMessage.indexOf(")");
			parsedMessage = parsedMessage.substring(0, endIndex-1);
			return parsedMessage.trim();
		} catch (Exception e) {
			return null;
		}
	}
}
