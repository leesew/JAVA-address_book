package jspbook.addrbook;

import java.sql.*;
import java.util.*;


public class AddrBean { 
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	/* MySQL 연결정보 */
	String jdbc_driver = "com.mysql.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3306/ezen?verifyServerCertificate=false&useSSL=false"; 
	
	// DB연결 메서드
	void connect() {
		try {
			Class.forName(jdbc_driver);

			conn = DriverManager.getConnection(jdbc_url,"root","java");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 수정된 주소록 내용 갱신을 위한 메서드
	public boolean updateDB(AddrBook addrbook) {
		connect();
		
		String sql ="update addrbook set abName=?, abEmail=?, abTel=?, abBirth=?, abCompany=?, abMemo=? where abId=?";		
		 
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,addrbook.getAbName());
			pstmt.setString(2,addrbook.getAbEmail());
			pstmt.setString(3, addrbook.getAbTel());
			pstmt.setString(4,addrbook.getAbBirth());
			pstmt.setString(5,addrbook.getAbCompany());
			pstmt.setString(6,addrbook.getAbMemo());
			pstmt.setInt(7,addrbook.getAbId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
	
	// 특정 주소록 게시글 삭제 메서드
	public boolean deleteDB(int gb_id) {
		connect();
		
		String sql ="delete from addrbook where abId=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,gb_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}
	
	// 신규 주소록 메시지 추가 메서드
	public boolean insertDB(AddrBook addrbook) {
		connect();
		// sql 문자열 , gb_id 는 자동 등록 되므로 입력하지 않는다.
				
		String sql ="insert into addrbook(abName, abEmail, abTel, abBirth, abCompany, abMemo) values(?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,addrbook.getAbName());
			pstmt.setString(2,addrbook.getAbEmail());
			pstmt.setString(3, addrbook.getAbTel());
			pstmt.setString(4,addrbook.getAbBirth());
			pstmt.setString(5,addrbook.getAbCompany());
			pstmt.setString(6,addrbook.getAbMemo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			disconnect();
		}
		return true;
	}

	// 특정 주소록 게시글 가져오는 메서드
	public AddrBook getDB(int gb_id) {
		connect();
		
		String sql = "select * from addrbook where abId=?";
		AddrBook addrbook = new AddrBook();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,gb_id);
			ResultSet rs = pstmt.executeQuery();
			
			// 데이터가 하나만 있으므로 rs.next()를 한번만 실행 한다.
			rs.next();
			addrbook.setAbId(rs.getInt(1));
			addrbook.setAbName(rs.getString(2));
			addrbook.setAbEmail(rs.getString(3));
			addrbook.setAbTel(rs.getString(4));
			addrbook.setAbBirth(rs.getString(5));
			addrbook.setAbCompany(rs.getString(6));
			addrbook.setAbMemo(rs.getString(7));
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return addrbook;
	}
	
	// 전체 주소록 목록을 가져오는 메서드
	public ArrayList<AddrBook> getDBList() {
		connect();
		ArrayList<AddrBook> dataList = new ArrayList<AddrBook>();
		
		String sql = "select * from addrbook order by abId desc";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				AddrBook addrbook = new AddrBook();
				
				addrbook.setAbId(rs.getInt(1));
				addrbook.setAbName(rs.getString(2));
				addrbook.setAbEmail(rs.getString(3));
				addrbook.setAbTel(rs.getString(4));
				addrbook.setAbBirth(rs.getString(5));
				addrbook.setAbCompany(rs.getString(6));
				addrbook.setAbMemo(rs.getString(7));
				dataList.add(addrbook);
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			disconnect();
		}
		return dataList;
	}
}