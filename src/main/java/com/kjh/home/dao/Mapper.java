package com.kjh.home.dao;

public interface Mapper {
	
	public void  joinDao(String mid, String mpw, String mname,String memail);//회원가입 메소드
	public int logincheck(String mid, String mpw);// 로그인 체크 메서드

}
