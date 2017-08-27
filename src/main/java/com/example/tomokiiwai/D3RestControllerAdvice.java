package com.example.tomokiiwai;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * コントローラー横断例外ハンドラー
 *
 * @author tomoki.iwai
 */
@RestControllerAdvice
public class D3RestControllerAdvice extends ResponseEntityExceptionHandler {
	/**
	 * {@link javax.validation.Valid}で失敗した場合のハンドリング
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		final Map<String, Set<String>> errors = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.groupingBy(
				// キー：フィールド名
				FieldError::getField,
				// 値：メッセージリスト
				Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toSet())));

		return ResponseEntity.ok(errors);
	}

	/**
	 * コントローラーの{@link org.springframework.web.bind.annotation.PathVariable}で失敗した場合のハンドリング
	 *
	 * @return {@link ModelAndView}
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String handleMethodArgumentTypeMismatch() {
		return "redirect:/404";
	}
}
