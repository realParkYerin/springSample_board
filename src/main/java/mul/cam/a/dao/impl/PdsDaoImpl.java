package mul.cam.a.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mul.cam.a.dao.PdsDao;
import mul.cam.a.dto.PdsDto;

/*4*/

@Repository
public class PdsDaoImpl implements PdsDao {
	@Autowired
	SqlSessionTemplate session;
	
	//namespace - ns변수설정
	String ns = "Pds.";

	/* 9 */
	@Override
	public List<PdsDto> pdslist() {
		return session.selectList(ns + "pdslist");
	}

	@Override
	public int uploadPds(PdsDto dto) {
		return session.insert(ns + "uploadPds", dto);
	}
}
