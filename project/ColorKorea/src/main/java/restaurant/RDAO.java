package restaurant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DatabaseUtil;

public class RDAO {

	

//	Connection conn = DatabaseUtil.getConnection();
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	// SQL 명령어
	private final String Restaurant_SELECT = "SELECT SEQ, NAME, ADDRESS, PHONE, CONTENT, AREA, TIME1, URL FROM RESTAURANT WHERE AREA = ? ";
	
	/**
	 * 로그인
	 * @param vo
	 * @return
	 */
	public List<RDTO> selectRestaurant(RDTO dto) {
		System.out.println("===> JDBC로 selectRestaurant() 기능처리");
		List<RDTO> restaurantList =new ArrayList<RDTO>();
		RDTO restaurant = null;
		try {
			conn = DatabaseUtil.getConnection();
			stmt = conn.prepareStatement(Restaurant_SELECT);
			stmt.setString(1, dto.getArea());
		
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				restaurant = new RDTO();
				restaurant.setSeq(rs.getInt("SEQ"));
				restaurant.setName(rs.getString("NAME"));
				restaurant.setAddress(rs.getString("ADDRESS"));
				restaurant.setPhone(rs.getString("PHONE"));
				restaurant.setContent(rs.getString("CONTENT"));
				restaurant.setArea(rs.getString("AREA"));
				restaurant.setTime1(rs.getString("TIME1"));
				restaurant.setUrl(rs.getString("URL"));
				
				restaurantList.add(restaurant);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.close(rs, stmt, conn);
		}
		return restaurantList;
	}
}
