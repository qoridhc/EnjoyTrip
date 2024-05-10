//package com.ssafy.user.model.dao;
//
//import java.security.NoSuchAlgorithmException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.ssafy.user.model.UserDto;
//import com.ssafy.util.DBUtil;
//
//public class UserDaoImpl implements UserDao
//{
//	private static UserDaoImpl dao;
//	
//	private UserDaoImpl() {}
//
//	public static UserDaoImpl getinstance()
//	{
//		dao = (dao == null) ? (new UserDaoImpl()) : (dao);
//		return dao;
//	}
//
////	@Override
////	public UserDto login(UserDto dto) throws SQLException {
////		Connection conn = null;
////		PreparedStatement pstmt = null;
////		ResultSet rst = null;
////		
////		String sql = "Select * from members where user_id = ? and user_password = ?";
////		
////		try {
////			conn = DBUtil.getConnection();
////			pstmt = conn.prepareStatement(sql);
////			pstmt.setString(1, dto.getId());
////			pstmt.setString(2, dto.getPw());
////			
////			rst = pstmt.executeQuery();
////			
////			if (rst.next()) { // 로그인성공
////				dto.setName(rst.getString(2));
////				dto.setEmail_1(rst.getString(4)); //email_id
////				dto.setEmail_2(rst.getString(5)); //email_domain
////				//join_date 안함
////			} else {
////				dto = null;
////			}
////		}finally {
////			DBUtil.close(rst, pstmt, conn);
////		}
////		System.out.println(dto);
////		return dto;
////	}
//
////	@Override
////	public void signup(UserDto dto) throws SQLException {
////		Connection conn = null;
////		PreparedStatement pstmt = null;
////		String sql = "insert into members "
////				+ "(user_id, user_name, user_password, email_id, email_domain) "
////				+ "values (?, ?, ?, ?, ?)";
////		
////		//중복된 id가 db에 있는지 확인 필요
////		
////		try {
////			conn = DBUtil.getConnection();
////			pstmt = conn.prepareStatement(sql);
////			pstmt.setString(1, dto.getId());
////			pstmt.setString(2, dto.getName());
////			pstmt.setString(3, dto.getPw());
////			pstmt.setString(4, dto.getEmail_1());
////			pstmt.setString(5, dto.getEmail_2());
////			
////			System.out.println(pstmt);
////			
////			//ret값 받아서 결과 확인 필요
////			pstmt.executeUpdate();
////			
////		}finally {
////			DBUtil.close(pstmt, conn);
////		}
////	}
//	
//	@Override
//    public UserDto login(UserDto dto) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rst = null;
//        String saltSql = "Select salt from members where user_id = ?";
//        String sql = "Select * from members where user_id = ? and user_password = ?";
//        
//        try {
//            conn = DBUtil.getConnection();
//            pstmt = conn.prepareStatement(saltSql);
//            pstmt.setString(1, dto.getId());
//            rst = pstmt.executeQuery();
//
//            String salt = null;
//            if (rst.next()) {
//                salt = rst.getString("salt"); // 데이터베이스에서 저장된 솔트
//            }
//
//            if (salt != null) {
//                String inputHash = null;
//                try {
//                    inputHash = PasswordHasing.hashing(dto.getPw().getBytes(), salt);
//                } catch (NoSuchAlgorithmException e) {
//                    e.printStackTrace();
//                }
//
//                DBUtil.close(rst, pstmt, null); 
//                
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1, dto.getId());
//                pstmt.setString(2, inputHash); 
//
//                rst = pstmt.executeQuery();
//
//                if (rst.next()) {
//                    // 로그인 성공
//                    dto.setName(rst.getString("user_name"));
//                    dto.setEmail_1(rst.getString("email_id"));
//                    dto.setEmail_2(rst.getString("email_domain"));
//                } else {
//                    // 비밀번호 불일치 또는 사용자 ID가 존재하지 않음
//                    dto = null;
//                }
//            } else {
//                // 사용자 ID가 존재하지 않음
//                dto = null;
//            }
//        } finally {
//            DBUtil.close(rst, pstmt, conn);
//        }
//        System.out.println(dto);
//        return dto;
//    }
//	
//	
//	@Override
//    public void signup(UserDto dto) throws SQLException {
//        
//        String salt = PasswordHasing.getSalt(); // 솔트 생성
//        String hashedPassword = null;
//        try {
//            hashedPassword = PasswordHasing.hashing(dto.getPw().getBytes(), salt);
//        } catch (NoSuchAlgorithmException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//        
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        String sql = "insert into members "
//                + "(user_id, user_name, user_password, email_id, email_domain, salt) "
//                + "values (?, ?, ?, ?, ?, ?)";
//        
//        //중복된 id가 db에 있는지 확인 필요
//        
//        try {
//            
//            conn = DBUtil.getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, dto.getId());
//            pstmt.setString(2, dto.getName());
//            pstmt.setString(3, hashedPassword);
//            pstmt.setString(4, dto.getEmail_1());
//            pstmt.setString(5, dto.getEmail_2());
//            pstmt.setString(6, salt);
//            System.out.println(pstmt);
//            
//            //ret값 받아서 결과 확인 필요
//            int ret = pstmt.executeUpdate();
//            
//            System.out.println(ret);
//            
//        }finally {
//            DBUtil.close(pstmt, conn);
//        }
//    }
//
//	@Override
//	public void modify(UserDto dto) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
////		String sql = "update members "
////				+ "SET user_password = ? , email_id = ? , email_domain = ? "
////				+ "where user_id = ?";
//		String sql = "update members "
//				+ "SET user_password = ? "
//				+ "where user_id = ?";
//
//		try {
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			
////			pstmt.setString(1, dto.getPw());
////			pstmt.setString(2, dto.getEmail_1());
////			pstmt.setString(3, dto.getEmail_2());
////			pstmt.setString(4, dto.getId());
//			pstmt.setString(1, dto.getPw());
//			pstmt.setString(2, dto.getId());
//			
//			System.out.println(pstmt);
//			
//			//c값 받아서 결과 확인 필요
//			pstmt.executeUpdate();
//			
//		}finally {
//			DBUtil.close(pstmt, conn);
//		}
//	}
//
//	@Override
//	public void delete(String id) throws SQLException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String sql = "delete from members "
//				+ "where user_id = ?"; //user_id는 유일한 값
//		
//		try {
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement(sql);
////			pstmt.setString(1, dto.getId()); //파라미터로 dto객체받은 경우
//			pstmt.setString(1,  id);
//			
//			System.out.println(pstmt);
//			
//			pstmt.executeUpdate(); //Delete문 수행
////			int c = pstmt.executeUpdate();
////			System.out.println(c);
////			if (c>0) {
////				return true; //삭제성공
////			} else {
////				return false; //실패
////			}
//		} finally {
//			DBUtil.close(pstmt, conn);
//		}
//	}
//	
//	
//}
