package com.example.tomokiiwai;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * フォーム
 *
 * @author tomoki.iwai
 */
@Data
public class D3Form {
	@NotEmpty(message = "{name.empty}")
	private String name;
}
