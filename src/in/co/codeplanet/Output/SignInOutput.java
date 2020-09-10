package in.co.codeplanet.Output;

import java.util.List;

public class SignInOutput {
	
	private Boolean status;
	private List<Object> data;
	private List<Object> error;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public List<Object> getError() {
		return error;
	}
	public void setError(List<Object> error) {
		this.error = error;
	}
}
