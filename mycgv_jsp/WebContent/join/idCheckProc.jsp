<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv_jsp.dao.MemberDao" %>
<%
	String id = request.getParameter("id");
	MemberDao memberDao = new MemberDao();	
	int result = memberDao.idCheck(id);
	
	//Ajax로 전송되는 데이터는 반드시!! 문자열 타입으로 전송한다!!! 꼭 기억하기! - 지금 result는 int 타입 -> 문자열로 형변환 메소드  String.valueOf(변환하려는 값)사용해서 변경하기! 
	out.write(String.valueOf(result));
	
%>