package mul.cam.a.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import mul.cam.a.service.PdsService;

/*23*/
/*우리가 가지고있는 view위에 윈도우가 작업하고 있음 -> 그 윈도우가 DownloadView*/
public class DownloadView extends AbstractView{
	
	@Autowired
	PdsService service;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DownloadView enderMergedOutputModel");
		
		/* 28 */
		File downloadFile = (File)model.get("downloadFile");
		String filename = (String)model.get("filename"); //원본 파일명
		int seq = (Integer)model.get("seq"); //다운로드 카운드 수 증가
		
		//다운로드 받을 준비
		response.setContentType(this.getContentType());
		response.setContentLength((int)downloadFile.length());
		
		//한글 파일명 정상적으로 보이기
		filename = URLEncoder.encode(filename, "utf-8");
		
		//다운로드 창이 실제로 나오도록
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";"); //다운로드를 받았을 때 원본 파일명으로 change
		response.setHeader("Content-Transfer-Encoding", "binary;"); //인코딩 방식 - 바이너리형태
		response.setHeader("Content-Length", "" + downloadFile.length()); //내용의 길이(bar)
		response.setHeader("Pragma", "no-cache;"); //잠깐 저장할래? no
		response.setHeader("Expires", "-1;");//기한? no필요
		
		OutputStream os = response.getOutputStream(); //생성작업
		FileInputStream fis = new FileInputStream(downloadFile);
		
		//핵심!!
		//실제 data를 기입하는 part
		FileCopyUtils.copy(fis, os); //파일에 복사하는 util
		
		//download count ++
		
		if(fis != null) {
			fis.close();
		}
	}

}
