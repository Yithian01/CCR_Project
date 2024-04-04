package dao;

import dto.StuffDTO;
import exception.SearchWrongException;

import java.util.List;

public interface StuffDAO {
	
	/**
	 * 재료 테이블 보기 
	 * SELECT * from mydb.stuff;
	 */
	List<StuffDTO> selectStuff() throws SearchWrongException;
	
}
