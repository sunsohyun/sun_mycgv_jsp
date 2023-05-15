<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.mycgv_jsp.vo.MemberVo" %>
<%@ page import = "com.mycgv_jsp.dao.MemberDao" %>

<jsp:useBean id="memberVo" class="com.mycgv_jsp.vo.MemberVo"/>
<jsp:setProperty property="*" name="memberVo"/>

<%
	MemberDao memberDao = new MemberDao();
	int result = memberDao.loginCheck(memberVo);
	if(result == 1){//회원가입 성공 -> 메인화면으로 페이지 이동

		//세션객체에 id를 저장 ==> sid(name) = test(value)
		session.setAttribute("sid", memberVo.getId());
		
		out.write("<script>");
		out.write("alert('로그인에 성공하셨습니다.');");
		out.write("location.href='http://localhost:9000/mycgv_jsp/index.jsp';");
		out.write("</script>");
		//response.sendRedirect("http://localhost:9000/mycgv_jsp/index.jsp");
	}else{
		//로그인 실패 - 방법1.alert 메시지 띄우기(MVC1)  또는  방법2.로그인 실패 페이지로 이동(MVC2)
		out.write("<script>");
		out.write("alert('아이디 또는 패스워드가 다릅니다. 다시 진행해주세요.');");
		out.write("location.href='http://localhost:9000/mycgv_jsp/login/login.jsp';"); /* --> 여기에 붙어서 session이 넘어간다! */
		out.write("</script>");
	}
%>






