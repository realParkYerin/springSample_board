
package mul.cam.a.dao;

import java.util.List;

import mul.cam.a.dto.MemberDto;

public interface MemberDao {

	List<MemberDto> allMember();
	
	int idCheck(String id);
	
	//Member.xml - 회원가입 sql문 addmember 함수 생성
	int addMember(MemberDto dto);
	
	MemberDto login(MemberDto dto);
	
	
}