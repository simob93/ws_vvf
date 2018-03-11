package vvfriva.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class CustomJsonResponse<T> {
	
	private Boolean success;
	private List<String> message;
	private T data;
	private Integer total;

	public CustomJsonResponse() {
	}

	@JsonCreator
	public CustomJsonResponse(@JsonProperty("success") Boolean success, @JsonProperty("message") List<String> message,
			@JsonProperty("data") T data) {

		this.success = success;
		this.message = message;
		this.data = data;
	}
	
	@JsonCreator
	public CustomJsonResponse(@JsonProperty("success") Boolean success, @JsonProperty("message") List<String> message,
			@JsonProperty("data") T data, @JsonProperty("total") Integer total) {

		this.success = success;
		this.message = message;
		this.data = data;
		this.total = total;
	}
	
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
