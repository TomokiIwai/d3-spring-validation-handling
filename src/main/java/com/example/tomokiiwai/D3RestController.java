package com.example.tomokiiwai;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * コントローラー
 *
 * @author tomoki.iwai
 */
@RestController
public class D3RestController {
	/**
	 * JSON送信（メソッド内ハンドリング）
	 */
	@RequestMapping("/sendJson")
	public Object index(@Valid @RequestBody final D3Form form, final BindingResult br) {
		if (br.hasErrors()) {
			return br.getFieldErrors().stream().collect(Collectors.groupingBy(
					// キー：フィールド名
					FieldError::getField,
					// 値：メッセージリスト
					Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toSet())));
		}

		return "OK";
	}

	/**
	 * JSON送信（グローバルハンドリング）
	 */
	@RequestMapping("/sendJson2")
	public Object index(@Valid @RequestBody final D3Form form) {
		return "OK";
	}
}
