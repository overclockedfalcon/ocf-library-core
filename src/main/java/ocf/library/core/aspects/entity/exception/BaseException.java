package ocf.library.core.aspects.entity.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BaseException {
	private String errorCode;
	private String errorMessage;
	private List<ChildException> exceptions;
	private LocalDateTime timeStamp;

}
