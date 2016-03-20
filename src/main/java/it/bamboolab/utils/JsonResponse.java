package it.bamboolab.utils;

public class JsonResponse {

	private String message;
	private Object data;
	private String error;
	
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	
}
