<%@page import="dbcp_member2.MemberVO"%>
<%@page import="dbcp_member2.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% //로그인처리
    //loginForm.jsp에서 사용자가 입력한 current_password를 가져온 후
    //DB를 거쳐 확인 후 존재하는 사용자라면 세션에 담기
    String id=request.getParameter("userid");
    String password=request.getParameter("current_password");
    
    MemberDAO dao=new MemberDAO();
    MemberVO vo=dao.isLogin(id,password);
    if(vo!=null){//존재하는 사용자이기 때문에 세션에 정보 담기
    	session.setAttribute("vo", vo);
    	out.print("<script>");
    	out.print("alert('로그인 성공');");
    	out.print("location.href='loginForm.jsp'");
    	out.print("</script>");
    }else{
    	out.print("<script>");
    	out.print("alert('아이디와 비밀번호를 확인해주세요');");
    	out.print("location.href='loginForm.jsp'");
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