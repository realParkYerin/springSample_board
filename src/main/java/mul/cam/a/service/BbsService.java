package mul.cam.a.service;

import java.util.List;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

public interface BbsService {

	List<BbsDto> bbslist(BbsParam bbs);	
	int getAllBbs(BbsParam bbs);
	
	boolean writeBbs(BbsDto dto);
	
	BbsDto getBbs(int seq);
	
	boolean updateBbs(BbsDto dto);
	
	boolean answerBbs(BbsDto dto);
	
	/* 매개변수가 동일하므로 함수 하나만 - 댓글(수정, 추가) */
	boolean commentWrite(BbsComment bbs);
	
	List<BbsComment> commentList(int seq);
	
	
}
