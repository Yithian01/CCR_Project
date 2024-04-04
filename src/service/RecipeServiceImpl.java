package service;

import dao.RecipeDAO;
import dao.RecipeDAOImpl;
import dto.RecipeDTO;
import exception.SearchWrongException;

import java.util.List;

public class RecipeServiceImpl implements RecipeService {
	private RecipeServiceImpl() {};
	private static RecipeServiceImpl instance = new RecipeServiceImpl();
	private static RecipeDAO recipeDAO = RecipeDAOImpl.getInstance();
	
	public static RecipeService getInstance() {
		return instance;
	}
	
	@Override
	public List<RecipeDTO> selectRecipe() throws SearchWrongException {
		 List<RecipeDTO> list = recipeDAO.selectRecipe();
		 
		return list;
	}

	@Override
	public List<RecipeDTO> selectByMakeRecipe() throws SearchWrongException {
		 List<RecipeDTO> list = recipeDAO.selectByMakeRecipe();
		 
		return list;
	}

}
