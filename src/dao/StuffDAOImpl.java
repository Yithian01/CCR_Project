package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.StuffDTO;
import exception.SearchWrongException;
import util.*;


public class StuffDAOImpl implements StuffDAO {
	
	private StuffDAOImpl() {};
	private static StuffDAOImpl instance = new StuffDAOImpl() {};
	public static StuffDAOImpl getInstance() {
		return instance;
	}
	

	@Override
	public List<StuffDTO> selectStuff() throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * from mydb.stuff;"; 
		
		List<StuffDTO> list = new ArrayList<>();
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int cnt =1;
				list.add(new StuffDTO( rs.getString(cnt++), rs.getInt(cnt++), rs.getInt(cnt++) ) );
			}
		}catch( SQLException e) {
			e.printStackTrace();
			throw new SearchWrongException("DB쿼리에 문제가 발생하여 조회할 수 없습니다.");
		}finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

}
