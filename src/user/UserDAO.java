package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserDAO {
	//비밀번호를 받아서 로그인을 시도해주는 함수
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1;//로그인 ㅣ성공
				}else {
					return 0;//비밀번호 틀림 
				}
			}
			return -1;//아이디없음
		}catch(Exception e) {
			e.printStackTrace();
		}finally {//접근한 이후에는 접근한 자원들을 전부 해제 해준다.
			try {if(conn != null) conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs != null) rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -2;//데이터베이스 오류
		
		
	}
	//사용자의 정수를 입력받아서 확인해주는 함수
	public int join(UserDTO user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, flase)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,user.getUserID());
			pstmt.setString(2,user.getUserPassword());
			pstmt.setString(3,user.getUserEmail());
			pstmt.setString(4,user.getUserEmailHash());
			rs = pstmt.executeQuery();
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {//접근한 이후에는 접근한 자원들을 전부 해제 해준다.
			try {if(conn != null) conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs != null) rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return -1;//회원가입실패
		
		
	}
	//사용자의 이메일 주소를 반환해주는 함수
	public String getUserEamil(String userID) {//유저의 이메일을 알아낸다
		String SQL = "SELECT userEmail FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {//접근한 이후에는 접근한 자원들을 전부 해제 해준다.
			try {if(conn != null) conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs != null) rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return null;//데이터베이스오류
	}
	
	//현재 이메일 인증이 되었는지 확인해주는 함수(T/F로 반환해준다)
	public boolean getUserEmailChecked(String userID) {
		String SQL = "SELECT userEmailChecked FROM USER WHERE userID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getBoolean(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {//접근한 이후에는 접근한 자원들을 전부 해제 해준다.
			try {if(conn != null) conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs != null) rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return false;//데이터베이스오류
		
		
	}
	//특정사용자의 이메일인증을 수행해준다
	public boolean setUserEmailChecked(String userID) {
		String SQL = "UPDATE USER SET userEMAILChecked = true WHERE userID =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			pstmt.executeUpdate();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {//접근한 이후에는 접근한 자원들을 전부 해제 해준다.
			try {if(conn != null) conn.close();}catch(Exception e) {e.printStackTrace();}
			try {if(pstmt != null) pstmt.close();}catch(Exception e) {e.printStackTrace();}
			try {if(rs != null) rs.close();}catch(Exception e) {e.printStackTrace();}
		}
		return false;//데이터베이스오류
		
		
	}
	
}

