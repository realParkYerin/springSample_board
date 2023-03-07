package mul.cam.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mul.cam.a.dto.PdsDto;
import mul.cam.a.service.PdsService;
import mul.cam.a.util.PdsUtil;

@Controller
public class PdsController {
	/* 6 */
	@Autowired
	PdsService service;
	
	/* 12 */
	@RequestMapping(value="pdslist.do",method = RequestMethod.GET)
	public String pdslist(Model model) { //들어오는 값은 없지만 보내줄 값은 있으므로 model use
		List<PdsDto> list = service.pdslist();
		//짐싸!
		model.addAttribute("pdslist",list);
		
		return "pdslist";
	}
	
	/* 17 */
	@GetMapping(value="pdswrite.do")
	//이동만 시켜줌
	public String pdswrite() {
		return "pdswrite"; //pdswrite.jsp
	}
	
	/* 19 - 업로드 part*/
	@PostMapping(value="pdsupload.do")
	public String pdsupload(PdsDto dto,
							@RequestParam(value="fileload",required = false)
							MultipartFile fileload,
							HttpServletRequest req) {
	
		/* 22 */
		//filename(original filename)취득 -> fileload 가 갖고있음
		String filename = fileload.getOriginalFilename(); //원본파일명 get
		//DB
		dto.setFilename(filename); //원본파일명(db)에 넣기위함(db에 저장)
		
		//upload의 경로 설정(방법-server/folder) 우리는 server use
		//server
		String fupload = req.getServletContext().getRealPath("/upload");//경로설정
		//folder
		//String fupload = "c://temp";
		
		System.out.println("fupload:" + fupload);
		
		//파일명을 충돌되지 않는 명칭으로 변경(Date)
		String newfilename = PdsUtil.getNewFileNmae(filename); //원본파일명 변경되서 newfilename에 담음
		
		dto.setNewfilename(newfilename); //변경된 파일명
		
		//파일준비
		File file = new File(fupload + "/" + newfilename);
		
		try {
			//실제로 파일 생성 + 기입 = 업로드
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			
			//db에 저장
			service.uploadPds(dto);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/pdslist.do"; //controller에서 controller로 이동 -> redirect
	}
	
	/* 25 */
	@PostMapping(value="filedownLoad.do")
	public String filedownLoad(int seq, String filename, String newfilename, Model model, HttpServletRequest req) {
		//seq, filename, newfilename 값을 DownloadView에 보내야 함
		
		/* 27 */
		//경로
		//server
		String fupload = req.getServletContext().getRealPath("/upload");//경로설정
		//다운로드 받을 파일
		File downloadFile = new File(fupload + "/" + newfilename);
		
		//짐싸!
		model.addAttribute("downloadFile", downloadFile); //file - 실제 업로드되어 있는 파일명	3545464.txt
		model.addAttribute("filename", filename); //String - 오리지널 파일명					abc.txt
		model.addAttribute("seq", seq); //int - for 다운로드 카운드 증가
		
		return "downloadView";
	}

}
