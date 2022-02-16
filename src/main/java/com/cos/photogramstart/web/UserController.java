package com.cos.photogramstart.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;


	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
		model.addAttribute("dto", dto);
		return "user/profile";
	}

	/*	세션정보 찾기를 통해 회원 정보 변경하기!
		/auth/login -> username이 존재하면? -> PrincipalDetails에서 세션 저장
		-> autnentication에 접근(AuthenticationPrincipal 어노테이션 이용) -> 세션 접근!!
 	*/

	// 회원 정보 변경
	// model에 담아서 세션정보 보내기-> pom.xml에서 처리했으므로(시큐리티 태그 라이브러리, header)할 필요 없음!
	@GetMapping("/user/{id}/update")
	public String updateForm(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		// 1. 추천
		System.out.println("세션 정보 : "+principalDetails.getUser());
		
		// 2. 비효율
		//Authentication auth =   SecurityContextHolder.getContext().getAuthentication();
		//PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
		//System.out.println("직접 찾은 세션 정보 : "+mPrincipalDetails.getUser());
	
		return "user/update";
	}

}
