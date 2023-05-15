package com.mycgv_jsp.dao;

import java.util.ArrayList;

import com.mycgv_jsp.vo.BoardVo;
import com.mycgv_jsp.vo.MemberVo;

public class MemberDao extends DBConn {
	/**
	 * insert - 회원가입
	 */
	public int insert(MemberVo memberVo) {
		int result = 0;
		String sql = "insert into mycgv_member(id,pass,name,gender,email,addr,tel,pnumber,hobbylist,intro,mdate,grade)"
					+ " values(?,?,?,?,?,?,?,?,?,?,sysdate,'GOLD')";
		getPreparedStatement(sql);
		
		try {
			//매핑
			pstmt.setString(1, memberVo.getId());
			pstmt.setString(2, memberVo.getPass());
			pstmt.setString(3, memberVo.getName());
			pstmt.setString(4, memberVo.getGender());
			pstmt.setString(5, memberVo.getEmail());
			pstmt.setString(6, memberVo.getAddr());
			pstmt.setString(7, memberVo.getTel());
			pstmt.setString(8, memberVo.getPnumber());
			pstmt.setString(9, memberVo.getHobbyList());
			pstmt.setString(10, memberVo.getIntro());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * idCheck - 아이디 중복체크 
	 */
	public int idCheck(String id) {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM MYCGV_MEMBER WHERE ID = ? ";	//int니까 count 써야함!!!!!!!!
		
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * loginCheck - 로그인 체크
	 */
	public int loginCheck(MemberVo memberVo) {
		int result = 0;
		String sql = "SELECT COUNT(*) FROM MYCGV_MEMBER WHERE ID=? AND PASS=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, memberVo.getId());
			pstmt.setString(2, memberVo.getPass());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 관리자 - 회원전체리스트 출력: select
	 */
	public ArrayList<MemberVo> select(){
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		String sql = "SELECT ROWNUM RNO, ID, NAME, TO_CHAR(MDATE,'YYYY-MM-DD') MDATE, GRADE "
				+ " FROM (SELECT ID, NAME, MDATE, GRADE FROM MYCGV_MEMBER ORDER BY MDATE DESC)";
		
		getPreparedStatement(sql);
		
		try {
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo memberVo = new MemberVo();
				
				memberVo.setRno(rs.getInt(1));
				memberVo.setId(rs.getString(2));
				memberVo.setName(rs.getString(3));
				memberVo.setMdate(rs.getString(4));
				memberVo.setGrade(rs.getString(5));

				list.add(memberVo);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
