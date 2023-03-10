package mul.cam.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mul.cam.a.dao.PdsDao;
import mul.cam.a.dto.PdsDto;
import mul.cam.a.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService {
	/* 5 */
	@Autowired
	PdsDao dao;
	
	/* 11 */
	@Override
	public List<PdsDto> pdslist() {
		return dao.pdslist();
	}

	@Override
	public boolean uploadPds(PdsDto dto) {
		int count = dao.uploadPds(dto);
		return count>0?true:false;
	}
}
