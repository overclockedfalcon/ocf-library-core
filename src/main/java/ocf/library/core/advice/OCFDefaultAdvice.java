package ocf.library.core.advice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ocf.library.core.aspects.entity.exception.BaseException;
import ocf.library.core.aspects.entity.exception.ChildException;
import ocf.library.core.enums.ErrorCodes;

@ControllerAdvice
public class OCFDefaultAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> defaultExceptionHandler(Exception exception) {
		BaseException baseException = createBaseException(ErrorCodes.INTERNAL_SERVER_ERROR.getErrorCode(),
				ErrorCodes.INTERNAL_SERVER_ERROR.getErrorMessage());
		populateChildExceptions(baseException, ErrorCodes.INTERNAL_SERVER_ERROR.getErrorCode(), exception.getMessage(),
				exception.getCause().getClass().getSimpleName());
		return new ResponseEntity(baseException, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	private void populateChildExceptions(BaseException baseException, String errorCode, String errorMessage,
			String errorKey) {
		if (Objects.isNull(baseException))
			baseException = createBaseException(ErrorCodes.INTERNAL_SERVER_ERROR.getErrorCode(),
					ErrorCodes.INTERNAL_SERVER_ERROR.getErrorMessage());
		if (CollectionUtils.isEmpty(baseException.getExceptions()))
			baseException.setExceptions(new ArrayList<ChildException>());
		ChildException childException = new ChildException();
		childException.setErrorCode(errorCode);
		childException.setErrorMessage(errorMessage);
		childException.setErrorKey(errorKey);
		baseException.getExceptions().add(childException);
	}

	private BaseException createBaseException(String errorCode, String errorMessage) {
		BaseException baseException = new BaseException();
		baseException.setErrorCode(errorCode);
		baseException.setErrorMessage(errorMessage);
		baseException.setTimeStamp(LocalDateTime.now());
		return baseException;

	}

}
