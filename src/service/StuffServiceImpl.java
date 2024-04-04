package service;

import dao.StuffDAO;
import dao.StuffDAOImpl;
import dto.StuffDTO;
import exception.SearchWrongException;

import java.util.List;

public class StuffServiceImpl implements StuffService {
	private StuffServiceImpl() {}; 
	private static StuffServiceImpl instance = new StuffServiceImpl();
	private static StuffDAO stuffDAO = StuffDAOImpl.getInstance();
	
	public static StuffService getInstance() {
		return instance;
	}
	
	@Override
	public List<StuffDTO> selectStuff() throws SearchWrongException {
	
		List<StuffDTO> list = stuffDAO.selectStuff();
		 
		 return list;
	}
	
}
