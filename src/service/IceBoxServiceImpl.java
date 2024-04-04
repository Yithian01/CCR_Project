package service;

import java.util.List;

import dao.IceBoxDAO;
import dao.IceBoxDAOImpl;
import dto.IceBoxDTO;
import exception.DMLException;
import exception.SearchWrongException;


public class IceBoxServiceImpl implements IceBoxService {
	private IceBoxServiceImpl() {};
	
	private static IceBoxServiceImpl instance = new IceBoxServiceImpl();
	private static IceBoxDAO iceBoxDAO = IceBoxDAOImpl.getInstance();

	public static IceBoxService getInstance() {
		return instance;
	}
	
	@Override
	public List<IceBoxDTO> iceBoxSelectAll() throws SearchWrongException {
		List<IceBoxDTO> list = iceBoxDAO.iceBoxSelectAll();
	
		return list;
	}

	@Override
	public int insertIceBox(IceBoxDTO iceBox) {
		int result  = 0;
		
		result = iceBoxDAO.insertIceBox(iceBox);
		if (result <= 0 ) {
			throw new DMLException("재료등록 실패!");
		}
		
		return result;
	}

	@Override
	public int deleteIceBox(int boxNum) {
		int result  = 0;
		
		result = iceBoxDAO.deleteIceBox(boxNum);
		if (result <= 0 ) {
			throw new DMLException("재료 삭제 실패!");
		}
		
		return result;
	}

	
}
