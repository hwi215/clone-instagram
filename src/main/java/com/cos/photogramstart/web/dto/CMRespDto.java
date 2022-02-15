package com.cos.photogramstart.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// 공통 응답 Dto

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> { // <T> 제너릭 이용
	private int code; // 1(성공), -1(실패)
	private String message;
	private T data;
}
