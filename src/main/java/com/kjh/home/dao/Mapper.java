package com.kjh.home.dao;

import com.kjh.home.dto.MemberDto;

public interface Mapper {
	

	//회원 관리용
	public void  joinDao(String mid, String mpw, String mname,String memail);//회원가입 메소드
	public int logincheck(String mid, String mpw);// 로그인 체크 메서드
	public MemberDto memberinfo(String mid);//특정 아이디 회원정보 가져오기 메서드
	public void memberDelete(String mid);//회원정보 탈퇴하는 메서드
//-----------------------------------------------------------------------------------------------------------------------	
	//게시판용
	public void writeDao(String btitle, String bcontent ,String bmid,String bmname );//게시판 글쓰기
	
	
	
}
