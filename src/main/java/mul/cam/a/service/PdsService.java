package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.PdsDto;

public interface PdsService {
	/* 10 */
	List<PdsDto> pdslist();
	
	boolean uploadPds(PdsDto dto);

}
