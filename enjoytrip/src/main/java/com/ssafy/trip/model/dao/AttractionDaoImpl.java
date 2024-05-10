//package com.ssafy.trip.model.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ssafy.trip.model.AttractionDto;
//import com.ssafy.trip.model.GugunDto;
//import com.ssafy.trip.model.SidoDto;
//import com.ssafy.util.DBUtil;
//
//public class AttractionDaoImpl implements AttractionDao
//{
//	private static AttractionDaoImpl dao;
//
//	private AttractionDaoImpl() {}
//
//	public static AttractionDaoImpl getDao()
//	{
//		dao = (dao == null) ? (new AttractionDaoImpl()) : (dao);
//		return dao;
//	}
//
//	@Override
//	public List<SidoDto> getSido() throws SQLException {
//		List<SidoDto> list = new ArrayList<>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rst = null;
//		
//		String sql = "Select * from sido";
//		
//		try {
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			rst = pstmt.executeQuery();
//			
//			while(rst.next()) {
//				SidoDto sdo = new SidoDto();
//				sdo.setSido_code(rst.getString(1));
//				sdo.setSido_name(rst.getString(2));
//				list.add(sdo);
//			}
//		}finally {
//			DBUtil.close(rst, pstmt, conn);
//		}
//		
//		return list;
//	}
//
//	@Override
//	public List<GugunDto> getGugun(String sido_code) throws SQLException {
//		List<GugunDto> list = new ArrayList<>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rst = null;
//		
//		String sql = "select gugun_code, gugun_name from gugun where sido_code = ?";
//		
//		try {
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, sido_code);
//			rst = pstmt.executeQuery();
//			
//			while(rst.next()) {
//				GugunDto gdo = new GugunDto();
//				gdo.setGugun_code(rst.getInt(1));
//				gdo.setGugun_name(rst.getString(2));
//				list.add(gdo);
//			}
//		}finally {
//			DBUtil.close(rst, pstmt, conn);
//		}
//		
//		return list;
//	}
//
//	public List<AttractionDto> getAttractionList(String sido, String gugun, String keyword) throws SQLException {
//		List<AttractionDto> list = new ArrayList<>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rst = null;
//		
//		String sql = "select first_image, title, addr1, latitude, longitude "
//				+ "from attraction_info "
//				+ "where content_type_id = ? and sido_code = ? and gugun_code = ? "
//				+ "limit 10;";
//		
//		try {
//			conn = DBUtil.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, keyword);
//			pstmt.setString(2, sido);
//			pstmt.setString(3, gugun);
//			
//			rst = pstmt.executeQuery();
//			
//			while(rst.next()) {
//				AttractionDto ado = new AttractionDto();
//				ado.setFirst_image(rst.getString(1));
//				ado.setTitle(rst.getString(2));
//				ado.setAddr1(rst.getString(3));
//				ado.setLatitude(rst.getString(4));
//				ado.setLongitude(rst.getString(5));
//				list.add(ado);
//			}
//		}finally {
//			DBUtil.close(rst, pstmt, conn);
//		}
//		
//		return list;
//	}
//}
//
