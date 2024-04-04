package controller;

import dto.RecipeDTO;
import service.RecipeService;
import service.RecipeServiceImpl;
import view.AllRecipeView;
import view.RecipeView;

import java.util.List;

public class RecipeController {
	private static RecipeService recipeService = RecipeServiceImpl.getInstance();
	
	/**
	 * 모든 레시피 출력
	 * SELECT * from mydb.recipes;
	 */
	public void  selectRecipe() {
		List<RecipeDTO> list = recipeService.selectRecipe();
		
		new AllRecipeView( list );
		
	};
	
	/**
	 * 현재 만들 수 있는 레시피 출력
	 * SELECT * FROM mydb.recipes WHERE (MAINSTUFF in (select STUFFNAME from icebox ))  and  SIDESTUFF LIKE '%' || (SELECT GROUP_CONCAT(STUFFNAME SEPARATOR '|') FROM icebox) || '%';
	 */
	public void selectByMakeRecipe( String mainStuff, String sideStuff  ) {
		List<RecipeDTO> list = recipeService.selectByMakeRecipe();
		
		new RecipeView(list);
		
	};

	
	
}
