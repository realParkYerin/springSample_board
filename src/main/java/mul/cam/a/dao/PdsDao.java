package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.PdsDto;

public interface PdsDao {
	/* 8 */
	List<PdsDto> pdslist();
	
	/* 21 */
	int uploadPds(PdsDto dto);

}
