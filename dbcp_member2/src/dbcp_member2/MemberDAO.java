package dbcp_member2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	private Connection getConnection() {
		//Class.forName~~
		try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/MySQL");
			con=ds.getConnection();
		} catch (SQLException e) {			
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;		
	}
	private void close(Connection con,PreparedStatement pstmt,
									ResultSet rs) {
		try {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();			
			if(con!=null)
				con.close();			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private void close(Connection con,PreparedStatement pstmt) {
		try {				
				if(pstmt!=null)
				pstmt.close();			
				if(con!=null)
				con.close();			
		}catch(SQLException e) {
				e.printStackTrace();
		}
	}
	
	public int member_insert(MemberVO vo) {
		int result=0;
				
		try {
			con=getConnection();
			//트랜잭션
			con.setAutoCommit(false);
			String sql="insert into member values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getEmail());
			result=pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {			
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			close(con,pstmt);
		}		
		return result;
	}//insert 종료
	
	public MemberVO isLogin(String id,String password) {
		//id,password 정보에 해당하는 사용자 확인후 id와 name넘겨주기
		MemberVO vo=null;
		try {
			con=getConnection();
			con.setAutoCommit(false);
			String sql="select * from member where userid=? and password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if (rs.next()) {//하나라도 있따면
				vo=new MemberVO();
				vo.setUserid(rs.getString("userid"));//칼럼명or칼럼번호
				vo.setName(rs.getString("name"));
			}
			con.commit();
		}catch(Exception e) { 
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		
		return vo;
		
	}
	public boolean checkID(String userid) {
		boolean flag=false;
		
		try {
			con=getConnection();
			con.setAutoCommit(false);
			String sql="select * from member where userid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			if (rs.next()) {//하나라도 있따면
				flag=true;
			}
			con.commit();
		}catch(Exception e) { 
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			close(con,pstmt,rs);
		}
		
		return flag;
		
	}
	
}











