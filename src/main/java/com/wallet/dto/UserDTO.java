package com.wallet.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private Long id;

	@Length(min = 3, max = 50, message = "O nome deve conter entre 3 e 50 caracteres")
	private String name;

	@NotNull
	@Length(min = 6, message = "Minimo deve ser 6 caracteres")
	private String password;

	@Email(message = "Email invalido")
	private String email;

}
