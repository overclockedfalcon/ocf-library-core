package ocf.library.core.enums;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public enum ErrorCodes {
	INTERNAL_SERVER_ERROR("500.%s.50000", "Internal Server Error");

	ErrorCodes(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	private String errorCode;
	private String errorMessage;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Component
	public static class Injector {
		@Value("${api.code}")
		private int apiCode;

		@PostConstruct
		public void initialiseErrorCodes() {
			for (ErrorCodes errorCode : ErrorCodes.values()) {
				errorCode.setErrorCode(String.format(errorCode.getErrorCode(), apiCode));
			}
		}
	}
}
