package com.opengov.prd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExceptionUtil {

	public ServiceException prepareException(String errorCode, String errorName, Exception ex, ExceptionType errorType,
			PriorityType errorPriority) {
		return prepareServiceException(errorCode, errorName, ex, errorType, errorPriority,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ServiceException prepareException(String errorCode, String errorName, Exception ex, ExceptionType errorType,
			PriorityType errorPriority, HttpStatus httpStatus) {
		return prepareServiceException(errorCode, errorName, ex, errorType, errorPriority, httpStatus);
	}

	public ServiceException prepareException(String errorCode, String errorName, String msg, ExceptionType errorType,
			PriorityType errorPriority, HttpStatus httpStatus) {
		ServiceException appException = ServiceException.builder().priority(errorPriority.toString()).name(errorName)
				.code(errorCode).message(msg).type(errorType.toString()).httpStatus(httpStatus).build();

		return appException;
	}

	private ServiceException prepareServiceException(String errorCode, String errorName, Exception ex,
			ExceptionType errorType, PriorityType errorPriority, HttpStatus httpStatus) {

		ServiceException appException = ServiceException.builder().priority(errorPriority.toString()).name(errorName)
				.code(errorCode).message(ex.getMessage()).type(errorType.toString()).httpStatus(httpStatus).build();

		if (ex != null) {
			appException.initCause(ex);
		}

		return appException;
	}
}
