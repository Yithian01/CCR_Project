package controller;

import dto.FavoriteDTO;
import service.FavoriteService;
import service.FavoriteServiceImpl;
import view.FavoriteView;

import javax.swing.*;
import java.util.List;

public class FavoriteController extends JFrame  {
	private FavoriteService favoriteService  = FavoriteServiceImpl.getInstance();
	
	/**
	 * 즐겨찾기 목록 불러오기
	 * SELECT FAVORITESNUM, Favorites.CURRENT, STUFFNAME, (select sttype from stuff where Favorites.stuffname = stuff.STUFFNAME) as sttype FROM Favorites ;
	 */
	public void selectFavorite() {
		List<FavoriteDTO> list = favoriteService.selectFavorite();
	
		new FavoriteView(list);
		
	}
	
	
	/**
	 * 즐겨찾기 목록에 추가
	 *INSERT INTO mydb.favorites (CURRENT, STUFFNAME) SELECT COUNT(*) AS CURRENT, ? AS STUFFNAME FROM icebox WHERE icebox.STUFFNAME = ? ;
	 */
	public void insertFavorite(  FavoriteDTO favorite ) {
		
		int result = favoriteService.insertFavorite(favorite);
		

		JOptionPane.showMessageDialog(this, "추가 완료!!", "알림", JOptionPane.INFORMATION_MESSAGE);
	
	}
	
	/**
	 *  즐겨찾기 삭제 
	 *  DELETE FROM mydb.favorites WHERE favoritesNum = ?; 
	 */
	public void  deleteIceBox( int favoritesNum ) {
		int result = favoriteService.deleteIceBox(favoritesNum);
		
		JOptionPane.showMessageDialog(this, "삭제 완료!!", "알림", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
}
