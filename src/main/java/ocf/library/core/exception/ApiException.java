package ocf.library.core.exception;

public class ApiException extends RuntimeException{
	private String errorCode;
	private String errorMessage;
	private String errorAttribute;
	public ApiException(String errorCode, String errorMessage, String errorAttribute) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorAttribute = errorAttribute;
	}
	

}
