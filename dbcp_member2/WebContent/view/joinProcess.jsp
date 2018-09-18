<%@page import="dbcp_member2.MemberVO"%>
<%@page import="dbcp_member2.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//joinForm.jsp에서 넘긴 값 처리하기
	request.setCharacterEncoding("utf-8");

	//book_insertForm.jsp 값 가져오기
	String userid=request.getParameter("userid");
	String password=request.getParameter("password");
	String name=request.getParameter("name");
	String gender=request.getParameter("gender");
	String email=request.getParameter("email");
	
	//DAO 연동
	MemberDAO dao=new MemberDAO();
	
	//Data 삽입
	MemberVO vo=new MemberVO();
	vo.setUserid(userid);
	vo.setPassword(password);
	vo.setName(name);
	vo.setGender(gender);
	vo.setEmail(email);
	
	int result=dao.member_insert(vo);
	
	if(result>0){
		out.print("<script>");
		out.print("alert('회원가입 완료');");
		out.print("location.href='loginForm.jsp';");
		out.print("</script>");
	}else{
		out.print("<script>");
		out.print("alert('회원가입 실패');");
		out.print("history.back();");
		out.print("</script>");
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>