package mul.cam.a.dto;

import java.io.Serializable;

//초기값을 하나의 객체로 묶여서 Bbs.xml parameterType에 던져주기 위한 클래스
public class BbsParam implements Serializable{
	
	private String choice; //제목/내용/작성자
	private String search; //검색어
	private int pageNumber; //몇페이지냐 [1][2][3]
	
	//계산 필요
	private int start; //글 시작 번호
	private int end; //글 마지막 번호
	
	public BbsParam() {
	}

	public BbsParam(String choice, String search, int pageNumber, int start, int end) {
		super();
		this.choice = choice;
		this.search = search;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "BbsParam [choice=" + choice + ", search=" + search + ", pageNumber=" + pageNumber + ", start=" + start
				+ ", end=" + end + "]";
	}
	
}


	
	
