package service;

import dto.FavoriteDTO;
import exception.SearchWrongException;

import java.util.List;

public interface FavoriteService {
	/**
	 * 즐겨찾기 목록 불러오기
	 * SELECT FAVORITESNUM, Favorites.CURRENT, STUFFNAME, (select sttype from stuff where Favorites.stuffname = stuff.STUFFNAME) as sttype FROM Favorites ;
	 */
	List<FavoriteDTO> selectFavorite() throws SearchWrongException;
	
	/**
	 * 즐겨찾기 목록에 추가
	 *INSERT INTO mydb.favorites (CURRENT, STUFFNAME) SELECT COUNT(*) AS CURRENT, ? AS STUFFNAME FROM icebox WHERE icebox.STUFFNAME = ? ;
	 */
	int insertFavorite(FavoriteDTO favorite) ; 
	
	/**
	 *  즐겨찾기 삭제 
	 *  DELETE FROM mydb.favorites WHERE favoritesNum = ?; 
	 */
	int deleteIceBox(  int favoritesNum );
}
