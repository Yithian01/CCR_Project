package dao;

import dto.IceBoxDTO;
import exception.DMLException;
import exception.SearchWrongException;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IceBoxDAOImpl implements IceBoxDAO {

	private IceBoxDAOImpl() {};
	private static IceBoxDAOImpl instance = new IceBoxDAOImpl() {};
	public static IceBoxDAOImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<IceBoxDTO> iceBoxSelectAll() throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT boxNum, quantity , DATEDIFF( EXDATE, CURRENT_DATE()) AS leftDays, stuffName, ISFROEN, (select sttype from stuff where  mydb.icebox.STUFFNAME = stuff.STUFFNAME )as sttype FROM mydb.icebox order by  CASE WHEN DATEDIFF( EXDATE, CURRENT_DATE()) = 0 THEN 1 ELSE 0 END,  DATEDIFF( EXDATE, CURRENT_DATE())   ASC;";
		
		List<IceBoxDTO> list = new ArrayList<>();
		try {
			
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);	
			rs= ps.executeQuery();
			
			while(rs.next()) {
				int cnt =1;
				list.add( new IceBoxDTO(rs.getInt(1),rs.getInt(2),rs.getInt(3),  rs.getString(4) ,  rs.getInt(5) , rs.getInt(6)));
			}
			
		}catch(SQLException e) {
				e.printStackTrace();
				throw new SearchWrongException("DB쿼리에 문제가 발생하여 조회할 수 없습니다.");
		}finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
		
		
	}

	@Override
	public int insertIceBox(IceBoxDTO iceBox) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "INSERT INTO icebox (QUANTITY, EXDATE, ISFROEN, STUFFNAME) VALUES ( ? , CURRENT_DATE() + INTERVAL ( SELECT STDATE FROM stuff WHERE STUFFNAME = ?) DAY, ?, ?);";

		try {
			
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, iceBox.getQuantity());
			ps.setString(2, iceBox.getStuffName());
			ps.setInt(3, iceBox.getIsFrozen());
			ps.setString(4, iceBox.getStuffName());
			result = ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DMLException("DB 쿼리에 문제가 생겼습니다!!");
		}finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}
	
	@Override
	public int deleteIceBox(int boxNum) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = " DELETE FROM mydb.icebox WHERE boxNum = ?;";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1,boxNum );
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DMLException("DB 쿼리에 문제가 생겼습니다!!");
		}finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	

}










