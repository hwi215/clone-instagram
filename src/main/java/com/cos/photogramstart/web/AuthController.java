package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI 할때 사용(final 필드를 따름)
@Controller // 1. IoC 2. 파일을 리턴하는 컨트롤러
public class AuthController {

	private final AuthService authService;

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}

	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}

	/*
	회원가입 버튼 -> /auth/signup -> /auth/signin
	* 회원가입 버튼을 눌렀는데 아무 것 도 안뜸(404오류) 왜? -> CSRF 토큰이 실행중이기 때문
	* CSRF 토큰이란? 서버로 들어가기 전에  시큐리티 CSRF 토큰을 먼저 검사하는 구조임
	회원가입 페이지에 요청을 하면 응답(signup.jsp)하는데 응답 전에 CSRF 토큰이 생성
	정상적인 사용자인지 확인하기 위해 CSRF토큰을 사용(정상적인 경로로 접근한 사용자인지 확인하기 위해)
	여기선 CSRF 비활성화 할 것(기본적으로 활성화 되어있음) : SecurityConfig에서 비활성화 하기
	 */

	/*
	@Valid <- 유효성 검사 (spring-boot-starter-validation 의존성 추가 후 사용 가능)
	 */
	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key=value (x-www-form-urlencoded)
		// @Valid 유효성 검사, BindingResult 클래스:
		// User < - SignupDto(회원가입 시 SignupDto에 있는 객체에 값 넣기)
		User user = signupDto.toEntity(); // 생성
		authService.회원가입(user); // DB에 집어넣기(service 필요)
		//System.out.println(userEntity);
		// 로그를 남기는 후처리!!
		return "auth/signin";

	}
}
