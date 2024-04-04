package dao;

import java.util.List;

import dto.RecipeDTO;
import exception.SearchWrongException;

public interface RecipeDAO {

	/**
	 * 모든 레시피 출력
	 * SELECT * from mydb.recipes;
	 */
	List<RecipeDTO> selectRecipe() throws SearchWrongException;
	
	
	/**
	 * 현재 만들 수 있는 레시피 출력
	 * SELECT * FROM mydb.recipes WHERE (MAINSTUFF in (select STUFFNAME from icebox ))  and  SIDESTUFF LIKE '%' || (SELECT GROUP_CONCAT(STUFFNAME SEPARATOR '|') FROM icebox) || '%';
	 */
	List<RecipeDTO> selectByMakeRecipe( ) throws SearchWrongException;
	


	
}
