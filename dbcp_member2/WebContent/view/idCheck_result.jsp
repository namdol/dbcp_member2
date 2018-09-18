<%@page import="dbcp_member2.MemberVO"%>
<%@page import="dbcp_member2.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//joinForm.jsp에서 넘긴 값 처리하기
	request.setCharacterEncoding("utf-8");

	//book_insertForm.jsp 값 가져오기
	String userid=request.getParameter("userid");

	
	//DAO 연동
	MemberDAO dao=new MemberDAO();
	boolean result=dao.checkID(userid);
	
	
	if(result){
		out.print(1);
	}else{
		out.print(0);
	}
%>    
