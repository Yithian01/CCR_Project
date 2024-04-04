package service;

import dto.RecipeDTO;
import exception.SearchWrongException;

import java.util.List;

public interface RecipeService {
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
