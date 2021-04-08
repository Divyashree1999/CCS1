package com.cg.css.exceptionhandling;

import java.util.Date;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.aop.AopInvocationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * This class handle Exceptions at Application-level
 **/
@ControllerAdvice
public class EntityExceptionHandler {
	
	@ExceptionHandler(value = { InvalidUsernameOrPasswordException.class })
	public ResponseEntity<Object> handleInvalidUsernameOrPasswordException(InvalidUsernameOrPasswordException ex,
			WebRequest request) {
		ErrorMessage msg = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(value = { InvalidCardNumberException.class })
	public ResponseEntity<Object> handleInvalidCardNumberException(InvalidCardNumberException ex,
			WebRequest request) {
		ErrorMessage msg = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * This method handles MethodArgumentNotValidException
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<Object> handleNoCardsFoundException(MethodArgumentNotValidException ex, WebRequest request) {
		ErrorMessage msg = new ErrorMessage(new Date(),
				ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles NoCardsFoundException custom exception
	 **/
	@ExceptionHandler(value = { NoCardsFoundException.class })
	public ResponseEntity<Object> handleNoCardsFoundException(NoCardsFoundException ex, WebRequest request) {
		ErrorMessage msg = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles UsernameAlreadyExistsException custom exception
	 **/
	@ExceptionHandler(value = { UsernameAlreadyExistsException.class })
	public ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex,
			WebRequest request) {
		ErrorMessage msg = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles PasswordAndConfirmPasswordDoNotMatchException custom
	 * exception
	 **/
	@ExceptionHandler(value = { PasswordAndConfirmPasswordDoNotMatchException.class })
	public ResponseEntity<Object> handlePasswordAndConfirmPasswordDoNotMatchException(
			PasswordAndConfirmPasswordDoNotMatchException ex, WebRequest request) {
		ErrorMessage msg = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles AopInvocationException Exception
	 **/
	@ExceptionHandler(value = { AopInvocationException.class })
	public ResponseEntity<Object> handleDebitAmountMoreThanBalanceException(AopInvocationException ex,
			WebRequest request) {
		ErrorMessage msg = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles DebitAmountMoreThanBalanceException custom Exception
	 **/
	@ExceptionHandler(value = { DebitAmountMoreThanBalanceException.class })
	public ResponseEntity<Object> handleDebitAmountMoreThanBalanceException(DebitAmountMoreThanBalanceException ex,
			WebRequest request) {
		ErrorMessage msg = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles ReachedSwipingLimitException custom Exception
	 **/
	@ExceptionHandler(value = { ReachedSwipingLimitException.class })
	public ResponseEntity<Object> handleReachedSwipingLimitException(ReachedSwipingLimitException ex,
			WebRequest request) {
		ErrorMessage msg = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles EntityNotFoundException
	 **/
	@ExceptionHandler(value = { EntityNotFoundException.class })
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {

		ErrorMessage msg = new ErrorMessage(new Date(), ex.getLocalizedMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles NullPointerException
	 **/
	@ExceptionHandler(value = { NullPointerException.class })
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {

		ErrorMessage msg = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles IllegalStateException
	 **/
	@ExceptionHandler(value = { IllegalStateException.class })
	public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {

		ErrorMessage msg = new ErrorMessage(new Date(), ex.getMessage());
		return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles Exceptions thrown by the methods if @Valid doesn't pass
	 **/
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorMessage> handleExceptions(Exception e) {
		String message;
		Throwable cause, resultCause = e;
		while ((cause = resultCause.getCause()) != null && resultCause != cause) {
			resultCause = cause;
		}
		if (resultCause instanceof ConstraintViolationException) {
			message = (((ConstraintViolationException) resultCause).getConstraintViolations()).iterator().next()
					.getMessage();
		} else {
			resultCause.printStackTrace();
			message = "Unknown error";
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Date(), message));
	}

}
