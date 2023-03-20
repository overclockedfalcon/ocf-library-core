package ocf.library.core.aspects.entity.exception;

import lombok.Data;

@Data
public class ChildException {
	private String errorCode;
	private String errorMessage;
	private String errorKey;

}
