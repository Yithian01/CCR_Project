package controller;

import dto.IceBoxDTO;
import service.IceBoxServiceImpl;
import view.IceBoxView;

import javax.swing.*;
import java.util.List;

public class IceBoxController extends JFrame{
	private static service.IceBoxService IceBoxService = IceBoxServiceImpl.getInstance();
	
	
	
	/**
	 * 냉장고 속 재료보기 
	 *SELECT boxNum, quantity , DATEDIFF( EXDATE, CURRENT_DATE()) AS leftDays, stuffName, ISFROEN, (select sttype from stuff where  mydb.icebox.STUFFNAME = stuff.STUFFNAME )as sttype FROM mydb.icebox order by  CASE WHEN DATEDIFF( EXDATE, CURRENT_DATE()) = 0 THEN 1 ELSE 0 END,  DATEDIFF( EXDATE, CURRENT_DATE())   ASC;
	 */
	public void  iceBoxSelectAll() {
		List<IceBoxDTO> list = IceBoxService.iceBoxSelectAll();
		
		new IceBoxView(list);
		
	};
	
	/**
	 * 냉장고에 추가
	 *  INSERT INTO icebox (QUANTITY, EXDATE, ISFROEN, STUFFNAME) VALUES ( ? , CURRENT_DATE() + INTERVAL ( SELECT STDATE FROM stuff WHERE STUFFNAME = ?) DAY, ?, ?);
	 */
	public void  insertIceBox( IceBoxDTO iceBox ) {
		int result = IceBoxService.insertIceBox(iceBox);
		
		
		JOptionPane.showMessageDialog(this, "추가 완료!!", "알림", JOptionPane.INFORMATION_MESSAGE);
		
	};
	
	/**
	 *  냉장고 속 재료삭제 
	 *  DELETE FROM mydb.icebox WHERE boxNum = ?; 
	 **/
	public void  deleteIceBox(int boxNum   ) {
		int result = IceBoxService.deleteIceBox(boxNum);
		
		JOptionPane.showMessageDialog(this, "삭제 완료!!", "알림", JOptionPane.INFORMATION_MESSAGE);
		
	};

	
	
}
