package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookResponse {
	private String msg;
	private String id;
	
	// Getters & Setters
	
	@JsonProperty("Msg")
	public String getMsg() {
		return msg;
	}
	
	@JsonProperty("Msg")
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@JsonProperty("ID")
	public String getId() {
		return id;
	}
	
	@JsonProperty("ID")
	public void setId(String id) {
		this.id = id;
	}
	
	

}
