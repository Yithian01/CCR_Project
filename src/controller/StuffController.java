package controller;

import java.util.List;

import dto.StuffDTO;
import exception.SearchWrongException;
import service.StuffService;
import service.StuffServiceImpl;
import view.FavPlusView;
import view.StuffPlusView;

public class StuffController {
	private static StuffService stuffService =  StuffServiceImpl.getInstance();
	
	/**
	 * 재료 테이블 보기 
	 * SELECT * from mydb.stuff;
	 */
	public void  selectStuff( int channnel) {
		List<StuffDTO> list = stuffService.selectStuff();
		
		if (channnel == 1 ) {
			new StuffPlusView(list );
		}else if( channnel == 2) {
			
			new FavPlusView(list);
		}
		
	};
	
	
			
	
}
