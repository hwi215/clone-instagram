package com.cos.photogramstart.util;

// JS 이용(유효성 검사시 실패했을 경우 경고창 띄우고 자동으로 뒤로 돌아감)
public class Script {
	
	public static String back(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+msg+"');"); // msg 띄우기(경고창 띄우기)
		sb.append("history.back();"); // 경고창 띄우고 뒤로 돌아감
		sb.append("</script>");
		return sb.toString();
	}
}
