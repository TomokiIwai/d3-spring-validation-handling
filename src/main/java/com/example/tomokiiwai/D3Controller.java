package com.example.tomokiiwai;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * コントローラー
 *
 * @author tomoki.iwai
 */
@Controller
public class D3Controller {
	@ModelAttribute
	public D3Form setupForm() {
		return new D3Form();
	}

	/**
	 * トップページ
	 */
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * フォームデータ送信
	 *
	 * @param d3Form {@link D3Form}
	 * @param br     {@link BindingResult}
	 */
	@RequestMapping("/sendForm")
	public String sendForm(@Valid final D3Form d3Form, final BindingResult br, final RedirectAttributes redirectAttr) {
		// バリデーションエラー
		if (br.hasErrors()) {
			// エラー情報をリダイレクト先へ伝搬
			redirectAttr.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "d3Form", br);
			redirectAttr.addFlashAttribute("d3Form", d3Form);

			return "redirect:/";
		}

		return "sendForm";
	}
}
