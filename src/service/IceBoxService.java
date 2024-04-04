package service;

import dto.IceBoxDTO;
import exception.SearchWrongException;

import java.util.List;

public interface IceBoxService {
	/**
	 * 냉장고 속 재료보기 
	 *SELECT boxNum, quantity , DATEDIFF( EXDATE, CURRENT_DATE()) AS leftDays, stuffName, ISFROEN, (select sttype from stuff where  mydb.icebox.STUFFNAME = stuff.STUFFNAME )as sttype FROM mydb.icebox order by  CASE WHEN DATEDIFF( EXDATE, CURRENT_DATE()) = 0 THEN 1 ELSE 0 END,  DATEDIFF( EXDATE, CURRENT_DATE())   ASC;
	 */
	List<IceBoxDTO> iceBoxSelectAll() throws SearchWrongException;
	
	/**
	 * 냉장고에 추가
	 *  INSERT INTO icebox (QUANTITY, EXDATE, ISFROEN, STUFFNAME) VALUES ( ? , CURRENT_DATE() + INTERVAL ( SELECT STDATE FROM stuff WHERE STUFFNAME = ?) DAY, ?, ?);
	 */
	int insertIceBox( IceBoxDTO iceBox );
	
	/**
	 *  냉장고 속 재료삭제 
	 *  DELETE FROM mydb.icebox WHERE boxNum = ?; 
	 **/
	int deleteIceBox( int boxNum  ) ;
	
	
	
	
}
