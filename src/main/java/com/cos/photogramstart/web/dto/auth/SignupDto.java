package com.cos.photogramstart.web.dto.auth;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

// 요청하는 Dto
// username, pw, email, text 를 담고있음

@Data // Getter, Setter
public class SignupDto {
	@Size(min = 2, max = 20)
	@NotBlank // 무조건 입력 받아야 함
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	
	public User toEntity() { // SignupDto를 기반으로 객체 만들기
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
