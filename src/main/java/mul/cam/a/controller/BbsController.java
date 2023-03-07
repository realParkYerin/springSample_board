package mul.cam.a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mul.cam.a.dto.BbsComment;
import mul.cam.a.dto.BbsDto;
import mul.cam.a.dto.BbsParam;
import mul.cam.a.service.BbsService;

@Controller
public class BbsController {
	
	@Autowired
	BbsService service;
	
	@GetMapping(value="bbslist.do")
					//들어오는 값 - param / 짐싸서 보내기 위한 - model
	public String bbslist(BbsParam param, Model model) {
		
		// 글의 시작과 끝
		int pn = param.getPageNumber();  // 0 1 2 3 4
		int start = 1 + (pn * 10);	// 1  11
		int end = (pn + 1) * 10;	// 10 20 
		
		param.setStart(start);
		param.setEnd(end);
		
		List<BbsDto> list = service.bbslist(param);
		
		//페이지 총 갯수
		//총 길이값
		int len = service.getAllBbs(param);
		
		//게시글의 총 갯수가 아니라 페이지가 몇페이지인지 알고싶은거임
		int pageBbs = len / 10; 	//25/10 -> 2 나머지 5는 3page로 넘어감 +1필요
		if((len % 10)>0) {
			pageBbs = pageBbs + 1;
		}
		
		 if(param.getChoice() == null || param.getChoice().equals("")
				 || param.getSearch() == null || param.getSearch().equals("")) {
			 param.setChoice("검색"); 
			 param.setSearch("");
		 }
		 
		
		//짐싸!
		 model.addAttribute("bbslist", list);	// 게시판 리스트
		 model.addAttribute("pageBbs", pageBbs);	// 총 페이지수
		 model.addAttribute("pageNumber", param.getPageNumber()); // 현재 페이지
		 model.addAttribute("choice", param.getChoice());	// 검색 카테고리
		 model.addAttribute("search", param.getSearch());	// 검색어	
		
		return "bbslist";
	}
	
	@GetMapping(value = "bbswrite.do")
	public String bbswrite() {
		return "bbswrite";
	}
	
	/* 글 작성 후 완료를 누르면 메세지 호출이 아닌 bbslist로 바로 넘어감 */
	@PostMapping(value = "bbswriteAf.do")
	public String bbswriteAf(Model model, BbsDto dto) {
		boolean isS = service.writeBbs(dto);
		String bbswrite = "";		
		if(isS) {
			bbswrite = "BBS_ADD_OK";
		}else {
			bbswrite = "BBS_ADD_NG";
		}
		model.addAttribute("bbswrite", bbswrite);
		
		// return "message";
		return "redirect:/bbslist.do";	// controller에서  controller로 이동시 == sendRedirect대신
		// return "forward:/bbslist.do";	// controller에서  controller로 이동시 == forward대신
	}
	
	@GetMapping(value = "bbsdetail.do")
	public String bbsdetail(Model model, int seq) {
		BbsDto dto = service.getBbs(seq);
		model.addAttribute("bbsdto", dto);
		
		return "bbsdetail";
	}
	
	/* 수정 */
	@GetMapping(value = "bbsupdate.do")
	public String bbsupdate(Model model, int seq) {
		BbsDto dto = service.getBbs(seq);
		model.addAttribute("dto", dto);
		
		return "bbsupdate";
	}
	
	@GetMapping(value = "bbsupdateAf.do")
	public String bbsupdateAf(Model model, BbsDto dto) {
		System.out.println(dto.toString());
		boolean isS = service.updateBbs(dto);
		
		String bbsupdate = "BBS_UPDATE_OK";
		if(!isS) {			
			bbsupdate = "BBS_UPDATE_NG";
		}
		model.addAttribute("bbsupdate", bbsupdate);
		model.addAttribute("seq", dto.getSeq());
		
		return "message";
	}
	
	//답글
	@GetMapping(value = "answer.do")
	public String answer(Model model, int seq) {
		BbsDto dto = service.getBbs(seq);
		model.addAttribute("dto", dto);
		
		return "answer";
	}
	
	@PostMapping(value = "answerAf.do")
	public String answerAf(Model model, int seq, BbsDto dto) { //추가용
		//부모글 시퀀스 & 유저가 보내준 타이틀과 컨텐츠, 아이디 하나로 통합
		dto.setSeq(seq);		
		boolean isS = service.answerBbs(dto);
		String answer = "BBS_ANSWER_OK";
		if(isS == false) {
			answer = "BBS_ANSWER_NG";
		}
		model.addAttribute("answer", answer);
		
		return "message";
	}
	
	//댓글
	@PostMapping(value="commentWriteAf.do")
	public String commentWriteAf(BbsComment bbs) {
		boolean isS = service.commentWrite(bbs);
		//확인 코멘트
		if(isS) {
			System.out.println("댓글 작성에 성공~");
		}else {
			System.out.println("댓글 작성에 실패ㅜㅜ");
		}
		//seq넣어주기						seq넣어서 같이 보냄
		return "redirect:/bbsdetail.do?seq=" + bbs.getSeq();
	}
	
	//ajax로 넘김
	@ResponseBody
	@GetMapping(value = "commentList.do")
	public List<BbsComment> commentList(int seq){
		List<BbsComment> list = service.commentList(seq);
		return list;
	}
	
}

/*
 * 1.BbsDaoImpl(sql세션 생성) -> 2. BbsServiceImpl(다오 생성) -> 3.BbsController(서비스 생성)	
 * -> Bbs.xml(BbsDaoImpl에서 Bbs.xml 접근하기 위한 ns변수 생성)
 */