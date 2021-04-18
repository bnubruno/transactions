package com.challenge.transactions.controller;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.transactions.dto.response.RequestErrorDTO;
import com.challenge.transactions.exception.BadRequestException;
import com.challenge.transactions.exception.NotFoundException;

@RestController
@ControllerAdvice
@AllArgsConstructor
public class ExceptionAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public RequestErrorDTO handleValidationExceptions(MethodArgumentNotValidException ex) {
		return buildRequestErrorDTO( ex.getBindingResult().getAllErrors().stream()
				.map( error -> error.getDefaultMessage() )
				.collect( toList() ) );
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public RequestErrorDTO badRequestException(BadRequestException ex) {
		return buildRequestErrorDTO( List.of( ex.getMessage() ) );
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public RequestErrorDTO notFoundException(NotFoundException ex) {
		return buildRequestErrorDTO( List.of( ex.getMessage() ) );
	}

	private RequestErrorDTO buildRequestErrorDTO(List<String> messages) {
		return RequestErrorDTO.builder()
				.messages( messages )
				.timestamp( LocalDateTime.now() )
				.build();
	}

}