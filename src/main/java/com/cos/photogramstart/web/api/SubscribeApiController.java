package com.cos.photogramstart.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.SubscribeService;
import com.cos.photogramstart.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

// 파일 등이 아닌 데이터만 리턴하는 컨트롤러를 apiController라고 함
@RequiredArgsConstructor
@RestController
public class SubscribeApiController {
	
	private final SubscribeService subscribeService;

	// 현재 로그인 한 사람이 구독함 함
	@PostMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
		subscribeService.구독하기(principalDetails.getUser().getId(), toUserId);
		return new ResponseEntity<>(new CMRespDto<>(1, "구독하기 성공", null), HttpStatus.OK);
	}

	// 현재 로그인 한 사람이 구독취소 함
	@DeleteMapping("/api/subscribe/{toUserId}")
	public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId){
		subscribeService.구독취소하기(principalDetails.getUser().getId(), toUserId);
		return new ResponseEntity<>(new CMRespDto<>(1, "구독취소하기 성공", null), HttpStatus.OK);
	}
}
