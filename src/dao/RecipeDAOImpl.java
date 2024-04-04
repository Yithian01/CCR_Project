package dao;

import dto.RecipeDTO;
import exception.SearchWrongException;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAOImpl implements RecipeDAO {
	
	private RecipeDAOImpl() {};
	
	private static RecipeDAOImpl instance = new RecipeDAOImpl() {};
	public static RecipeDAOImpl getInstance() {
		return instance;
	}
	
	
	
	
	@Override
	public List<RecipeDTO> selectRecipe() throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * from mydb.recipes;"; 
		
		List<RecipeDTO>  list = new ArrayList<>();
		try {
			con = util.DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int cnt =1;
				list.add(new RecipeDTO( rs.getString(cnt++) ,rs.getString(cnt++) ,rs.getString(cnt++) ,rs.getString(cnt++)   ) );
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SearchWrongException("DB쿼리에 문제가 발생하여 조회할 수 없습니다.");
			}
		finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<RecipeDTO> selectByMakeRecipe() throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM mydb.recipes WHERE (MAINSTUFF in (select STUFFNAME from icebox ))  and  SIDESTUFF LIKE '%' || (SELECT GROUP_CONCAT(STUFFNAME SEPARATOR '|') FROM icebox) || '%';"; 
		
		List<RecipeDTO>  list = new ArrayList<>();
		try {
			con = util.DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int cnt =1;
				list.add(new RecipeDTO( rs.getString(cnt++) ,rs.getString(cnt++) ,rs.getString(cnt++) ,rs.getString(cnt++)   ) );
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SearchWrongException("DB쿼리에 문제가 발생하여 조회할 수 없습니다.");
			}
		finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

}
