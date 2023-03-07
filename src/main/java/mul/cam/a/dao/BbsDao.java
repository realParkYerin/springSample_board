package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;

public interface BbsDao {
	
	/* 글과 페이지 넘기기 */
	List<BbsDto> bbslist(BbsParam bbs);
	
	//모든 글의 갯수 allBbs
	int getAllBbs(BbsParam bbs);
	
	int writeBbs(BbsDto dto);
	
	BbsDto getBbs(int seq);
	
	//수정하기
	int updateBbs(BbsDto dto);
	
	//답글 - 수정, 추가(Bbs.xml에서)
	int answerBbsUpdate(BbsDto dto); //수정
	int answerBbsInsert(BbsDto dto); //추가
	
	//댓글
	int commentWrite(BbsComment bbs);
	//댓글목록
	List<BbsComment> commentList(int sql);
	
}