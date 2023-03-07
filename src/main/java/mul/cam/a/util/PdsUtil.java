package mul.cam.a.util;

import java.util.Date;

/*2.설정*/
public class PdsUtil {
	
	//파일명 -> 변경(time으로)
	//ex)myfile.txt(filename) -> 34521563.txt(newfilename)
	public static String getNewFileNmae(String filename) { //오리지널 파일 네임 들어옴
		String newfilename = "";
		String fpost = ""; //파일명의 위치
		
		//indexOf - .(도트)의 위치를 알려줌
		//확장자가 없는 파일이라면(.도트를 못찾으면) -1이 나옴
		if(filename.indexOf('.') >= 0) { //파일확장자가 있음
			
			//substring(시작위치 알기) - 시작위치(.도트)이므로 .부터 뒤에 확장자명까지 가져와라
			fpost = filename.substring(filename.indexOf('.')); //.txt
			newfilename = new Date().getTime() + fpost; // 34521563 + .txt
		}else { //파일확장자가 없음
			newfilename = new Date().getTime() + ".back"; //34521563 + .back
		}
		return newfilename;
	}
}
