package service;

import dao.FavoriteDAO;
import dao.FavoriteDAOImpl;
import dto.FavoriteDTO;
import exception.DMLException;
import exception.SearchWrongException;

import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {
	private FavoriteServiceImpl() {};
	private static FavoriteServiceImpl instance = new FavoriteServiceImpl();
	private static FavoriteDAO favoriteDAO = FavoriteDAOImpl.getInstance();
	
	public static FavoriteService getInstance() {
		return instance;
	}
	
	
	@Override
	public List<FavoriteDTO> selectFavorite() throws SearchWrongException {
		List<FavoriteDTO> list = favoriteDAO.selectFavorite();
		
		return list;	
	}

	@Override
	public int insertFavorite(FavoriteDTO favorite) {
	int result  = 0;
		
		result = favoriteDAO.insertFavorite(favorite);
		if (result <= 0 ) {
			throw new DMLException(" 입력 실패!");
		}
		
		return result;
	}

	@Override
	public int deleteIceBox(int favoritesNum) {
		int result  = 0;
		
		result = favoriteDAO.deleteIceBox(favoritesNum);
		if (result <= 0 ) {
			throw new DMLException(" 삭제 실패!");
		}
		
		return result;
	}

}
