package mul.cam.a.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mul.cam.a.dto.MemberDto;

//편집해주는 service인터페이스
public interface MemberService {
	
	List<MemberDto> allMember();
	
	//아이디 중복체크
	boolean idCheck(String id);
	//회원가입 체크
	boolean addMember(MemberDto dto);

	MemberDto login(MemberDto dto);
}
