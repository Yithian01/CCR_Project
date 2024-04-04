package dao;

import dto.FavoriteDTO;
import exception.SearchWrongException;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDAOImpl implements FavoriteDAO {

	private FavoriteDAOImpl() {};
	private static FavoriteDAOImpl instance = new FavoriteDAOImpl() {};
	public static FavoriteDAOImpl getInstance() {
		return instance;
	}
	
	
	
	@Override
	public List<FavoriteDTO> selectFavorite() throws SearchWrongException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT FAVORITESNUM, Favorites.CURRENT, STUFFNAME, (select sttype from stuff where Favorites.stuffname = stuff.STUFFNAME) as sttype FROM Favorites ;"; 
		
		List<FavoriteDTO>  list = new ArrayList<>();
		
		try {
			con = util.DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int cnt =1;
				list.add(new FavoriteDTO(  rs.getInt(cnt++), rs.getInt(cnt++), rs.getString(cnt++), rs.getInt(cnt++) ) );
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SearchWrongException("DB쿼리에 문제가 발생하여 조회할 수 없습니다.");
			}
		finally {
			util.DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}
	
	@Override
	public int insertFavorite(FavoriteDTO favorite) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "INSERT INTO mydb.favorites (CURRENT, STUFFNAME) SELECT COUNT(*) AS CURRENT, ? AS STUFFNAME FROM icebox WHERE icebox.STUFFNAME = ? ;";
	
		try {
			con = util.DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, favorite.getStuffName());
			ps.setString(2, favorite.getStuffName());
			result = ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SearchWrongException("DB쿼리에 문제가 발생하여 조회할 수 없습니다.");
			}
		finally {
			
			DBUtil.dbClose(con, ps);
			
		}
		return result;
		
	}

	@Override
	public int deleteIceBox(int favoritesNum) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "DELETE FROM mydb.favorites WHERE favoritesNum = ?;";
	
		try {
			con = util.DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, favoritesNum);
			result = ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SearchWrongException("DB쿼리에 문제가 발생하여 조회할 수 없습니다.");
			}
		finally {
			
			DBUtil.dbClose(con, ps);
			
		}
		return result;
	}

}
