package org.core.board.controller;
import java.io.File;
import org.core.util.CommonUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.core.board.model.service.BoardService;
import org.core.board.model.vo.Board;
import org.core.board.model.vo.BoardPageData;
import org.core.comment.model.service.CommentService;
import org.core.comment.model.vo.Comment;
import org.core.file.model.service.FileService;
import org.core.user.model.service.UserService;
import org.core.util.DateUtil;
import org.core.util.FileUtil;
import org.core.util.boardCodeNameprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import net.sf.json.JSONObject;
@Controller
public class BoardController {
	@Autowired
	@Qualifier("boardService")
	BoardService boardService;

	@Autowired
	@Qualifier("commentService")
	CommentService commentService;

	@Autowired
	@Qualifier("fileService")
	FileService fileService;

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@Resource(name = "fileUtil")
	private FileUtil fileUtil;
	// @Resource로 FileUtil.class를 사용할수 있게 추가

	@Resource(name = "DateUtil")
	private DateUtil DateUtil;
	
	@Resource(name="boardCodeNameprint")
	private boardCodeNameprint boardCodeNameprint;
	
	
	
	@RequestMapping("/ajax.do")
	public String moveajax() {
		return "board/study";
	}
	// 게시글쓰기 이동
	@RequestMapping("/writeBoard.do")
	public String moveWriteBoard() {
		return "board/writeboard";
	}
	// 게시글 쓰기
	@RequestMapping("/insertBoard.do")
	public String insertBoard(HttpServletRequest request, Model model, Board b, MultipartHttpServletRequest mrequest)throws Exception {
		
		int nBoardCodeNum     = Integer.parseInt(request.getParameter("boardCodeNum"));
		String strSelectPage  = request.getParameter("selectpage");
		String strSelect      = request.getParameter("select");
		String strBoardUserId = request.getParameter("userId");
		String strSelectParam = URLEncoder.encode("작성자" ,"UTF-8");
		String strDatePicker1 = request.getParameter("datepicker1");
		String strDatePicker2 = request.getParameter("datepicker2");
		
		
		if(CommonUtils.isEmpty(nBoardCodeNum)) {
			// null or ""일때 실행
			System.out.println("/insertBoard.do 의 nBoardCodeNum 값이 null 입니다. ");
		}else {
			// null or "" 아닐때 실행
			System.out.println("/insertBoard.do 의 nBoardCodeNum 값이 null이 아닙니다. ");
		}
		
		b.setSelect(strSelect);
		b.setSelectPage(strSelectPage);
		b.setBoardUserId(strBoardUserId);
		b.setBoardCodeNum(nBoardCodeNum);
		int result=boardService.insertBoard(b, mrequest); // 게시물insert하는 비즈니스 로직 수행
		
		
		
		if(result>0) { //글 쓰기가 성공적일시 
			return "redirect:/searchBoard.do?selectpage="+strSelectPage+"&select="+strSelectParam+"&search=&currentPage=1&boardCodeNum="+nBoardCodeNum+"&datepicker1="+strDatePicker1+"&datepicker2="+strDatePicker2;
		} // 글쓰기에 실패했을시 alert창 출력
		model.addAttribute("msg", "글쓰기 실패");
		model.addAttribute("url","writeboard.jsp");
	return "common/msg";
		
	}

	// 답변 쓰기 (부모의 gno,gseq,gdep 을 받아옴)
	@RequestMapping("/insertreply.do")
	public String insertreply(HttpServletRequest request, MultipartHttpServletRequest mrequest, Board b, Model model)throws Exception {
		
		int nBoardCodeNum     = Integer.parseInt(request.getParameter("boardCodeNum"));
		String strBoardUserId = request.getParameter("boardUserId");
		String strSelectParam = URLEncoder.encode("작성자" ,"UTF-8");
		String strDatePicker1 = request.getParameter("datepicker1");
		String strDatePicker2 = request.getParameter("datepicker2");
		b.setBoardCodeNum(nBoardCodeNum);
		b.setBoardUserId(strBoardUserId);
		int result            = boardService.insertreply(b, mrequest); // 게시물insert하는 비즈니스 로직 수행
		
		if(result > 0) {
			return "redirect:/searchBoard.do?selectpage=5&select="+strSelectParam+"&search=&currentPage=1&boardCodeNum="+nBoardCodeNum+"&datepicker1="+strDatePicker1+"&datepicker2="+strDatePicker2;
		}
		model.addAttribute("msg", "답변 쓰기 실패 !!");
		model.addAttribute("url","writereply.jsp");
	return "common/msg";
	};

	// 답변쓰기 이동
	@RequestMapping("/replymove.do")
	public String replymove(HttpServletRequest request, Board b, Model model) {

	   int nBoardGroupNo  = Integer.parseInt(request.getParameter("boardGroupNo")); // 답글쓸 GNO
	   int nBoardGroupSeq = Integer.parseInt(request.getParameter("boardGroupSeq")); // 답글쓸 부모의 SEQ
	   int nBoardGroupDep = Integer.parseInt(request.getParameter("boardGroupDep")); // 답글쓸 부모의 GDEP
	   int nBoardParentNo = Integer.parseInt(request.getParameter("boardParentNo")); // 답글쓸 부모의 BNO;
	   
	   b.setBoardGroupNo(nBoardGroupNo);
	   b.setBoardGroupSeq(nBoardGroupSeq);
	   b.setBoardGroupDep(nBoardGroupDep);
	   b.setBoardParentNo(nBoardParentNo);
	   model.addAttribute("b", b);
	   return "board/writereply";
	}

	// 게시글 수정+파일 업로드
	@RequestMapping("/updateinsertFile.do")
	public String updateinsertFile(HttpServletRequest request, Model model, Board b,MultipartHttpServletRequest mrequest) throws Exception {
		
		int nBoardCodeNum        = Integer.parseInt(request.getParameter("boardCodeNum"));
		int nBno                 = Integer.parseInt(request.getParameter("bno"));
		int nCurrentPage         = Integer.parseInt(request.getParameter("currentPage"));
	    String strBoardSubject   = request.getParameter("boardSubject"); 
		String strBoardContent   = request.getParameter("boardContent");
		String strDatePicker1    = request.getParameter("datepicker1");
		String strDatePicker2    = request.getParameter("datepicker2");
		Timestamp tsBoardRegdate = new Timestamp(System.currentTimeMillis());
		
		b.setBno(nBno);
		b.setBoardCodeNum(nBoardCodeNum);
		b.setBoardRegdate(tsBoardRegdate);
		b.setBoardContent(strBoardContent);
		b.setBoardSubject(strBoardSubject);
		int result               = boardService.updateinsertFile(b, mrequest); // 업데이트후 파일업로드
		
		if(result>0) {
			return "redirect:/boardClick.do?bno="+nBno+"&boardCodeNum="+nBoardCodeNum+"&currentPage=" + nCurrentPage+"&datepicker1="+strDatePicker1+"&datepicker2="+strDatePicker2;
		}
		model.addAttribute("msg", "게시글 수정 실패");
		model.addAttribute("url","boardupdate.jsp");
	return "common/msg";
	    
	}			

	// 파일 삭제 (파일번호와 게시글번호를 받아옴)
	@RequestMapping("/deleteFile.do")
	public String deleteFile(int fno, int bno, Model model,HttpServletRequest request) throws Exception {
		
		Board b                            = new Board();
		int nBoardCodeNum                  = Integer.parseInt(request.getParameter("boardCodeNum"));
		int nCurrentPage                   = Integer.parseInt(request.getParameter("currentPage"));
		List<Map<String, Object>> filelist = boardService.selectFilelist(bno);	
		String strDatePicker1              = request.getParameter("datepicker1");
		String strDatePicker2              = request.getParameter("datepicker2");
		b.setBno(bno);
		b.setFno(fno);
		b.setBoardCodeNum(nBoardCodeNum);
		int result                         = boardService.deleteFile(b);
		
		if(result > 0) {
			model.addAttribute("file", filelist);	
			return "redirect:/updatemove.do?bno="+bno+"&boardCodeNum="+nBoardCodeNum+"&currentPage=" + nCurrentPage+"&datepicker1="+strDatePicker1+"&datepicker2="+strDatePicker2;	
		}
		model.addAttribute("msg", "파일 삭제 실패");
		model.addAttribute("url","boardupdate.jsp");
	return "common/msg";
	};

	// 게시글 삭제
	@RequestMapping("/delete.do")
	public String deleteboard(Board board, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		
		Board b               = new Board(); // b 인스턴스 생성
		int nBoardCodeNum     = Integer.parseInt(request.getParameter("boardCodeNum"));
		int nBno              = Integer.parseInt(request.getParameter("bno"));
		String strSelectParam = URLEncoder.encode("작성자" ,"UTF-8");
		String strDatePicker1 = request.getParameter("datepicker1");
		String strDatePicker2 = request.getParameter("datepicker2");
		board.setBoardCodeNum(nBoardCodeNum);
		b.setBno(nBno);
		int result            = boardService.deleteboard(b); // 해당 게시글 번호에해당하는 내용 삭제
		
		if(result>0) {
			return "redirect:/searchBoard.do?selectpage=5&select="+strSelectParam+"&search=&currentPage=1&boardCodeNum="+nBoardCodeNum+"&datepicker1="+strDatePicker1+"&datepicker2="+strDatePicker2;
		}
		model.addAttribute("msg", "게시글 삭제 실패 ");
		model.addAttribute("url","boarddetail.jsp");
	return "common/msg";
	};
	
	//삭제 전 답변이있는지 출력
	 @ResponseBody
	  @RequestMapping("/delchk.do") 
	  public String delchk(Board b,HttpServletRequest request) throws Exception {
		 
	   int nBno            = Integer.parseInt(request.getParameter("bno"));
	   int nBoardGroupDep  = Integer.parseInt(request.getParameter("boardGroupDep"));
	   int nBoardGroupNo   = Integer.parseInt(request.getParameter("boardGroupNo"));
	   b.setBoardGroupNo(nBoardGroupNo); 
	   b.setBoardGroupDep(nBoardGroupDep); 
	   b.setBno(nBno);
	   
	   int result         = boardService.selectdelBoard(b); // 게시글 삭제 전 답변이있는지 없는지 체크
	  
	   JSONObject obj = new JSONObject(); //ajax 답변삭제시 
	   if(result == 0) {  // 답변이 0개라면 0
	   obj.put("result","0");
	   }else{ //답변이 있다면 1
	   obj.put("result","1");
	   }
	   return new Gson().toJson(obj);
	 };
	 
	// 검색 하기 
	
	@RequestMapping("/searchBoard.do")
	public String searchBoard(Model model, HttpServletRequest request, HttpServletResponse response,int boardCodeNum){
		
		Board board             = new Board();
		String strBoardCodeName = boardCodeNameprint.boardCodeNameprint(boardCodeNum);
		String strPath			= request.getServletPath();
		String strSearch        = request.getParameter("search"); // search라는 name의 값을 받아온다.
		String strSelect        = request.getParameter("select"); // select라는 name값을 받아온다.
		String strNowday        = DateUtil.nowday(); // DateUtil유틸
		String strSelectPage    = request.getParameter("selectpage");
		String strDatePicker1   = request.getParameter("datepicker1");
		String strDatePicker2   = request.getParameter("datepicker2");
		int nCurrentPage        = Integer.parseInt(request.getParameter("currentPage"));
		 
		board.setDatepick1(strDatePicker1);
		board.setDatepick2(strDatePicker2);
		board.setPath(strPath);
		board.setBoardCodeNum(boardCodeNum);
		board.setSearch(strSearch);
		board.setSelect(strSelect);
		board.setSelectPage(strSelectPage);
		
	    int nTotalSearchBoard  = boardService.searchTotalBoard(board); // 검색결과 총게시물 수
		board.setNo(nTotalSearchBoard);
		BoardPageData bpd      = boardService.searchBoard(board, nCurrentPage); // search의 입력한 값을 Like 연산자를 통해 결과값을 가져온다.
		
		
		if ( !bpd.getList().isEmpty() ) { // 가져온 결과값이 있다면
		model.addAttribute("boardCodeName", strBoardCodeName);
		model.addAttribute("currentPage", nCurrentPage);
		model.addAttribute("boardselect", board.getSelect());
		model.addAttribute("boardsearch", board.getSearch());
		model.addAttribute("board", board.getSelectPage());
		model.addAttribute("nowday", strNowday);
		model.addAttribute("No", board.getNo());
		model.addAttribute("total", nTotalSearchBoard);
		model.addAttribute("list", bpd.getList());
		model.addAttribute("start", bpd.getList().get(0).getStart());
		model.addAttribute("pageNavi", bpd.getPageNavi());
		return "board/boardList";
		}  //없다면 
		else {
			//System.out.println("???????????" + board);
			//System.out.println("total 값은 ? : " + nTotalSearchBoard);
			model.addAttribute("boardCodeName", strBoardCodeName);
			model.addAttribute("total", nTotalSearchBoard);
		}
	return "board/boardList";
}

	// 뒤로가기(인덱스로)  
	@RequestMapping("/back.do")
	public String backindex(HttpServletRequest request, Model model, Board board,HttpSession session) {
	
		 int nTodayBoard               = boardService.todayBoard();      // 오늘 등록한 게시물 갯수
		 int nUserCount                = userService.userCount();        // 총 회원수 카운트
		 int nTotal5Board              = boardService.total5Board();     // 5일 내 등록한 게시물 갯수
		 int nTotalBoard      		   = boardService.totalBoardMax();   // 총 게시물 
		 String strUserMax             = userService.userMax();          // 최다 게시글 등록자 명수
		 List<Board> lbList5           = boardService.boardList5(board); // 최근에 올라온게시글 5개
		 List<Board> lbMasterBoardlist = boardService.MasterBoardlist(); // 게시판 목록 출력
		 
		 Date day = new Date();        
		 SimpleDateFormat date         = new SimpleDateFormat("yyyy-MM-dd");
		 String toDay = date.format(day);
		  
		 Calendar mon = Calendar.getInstance();
		 mon.add(Calendar.MONTH , -1);
		 String monthDate             = new java.text.SimpleDateFormat("yyyy-MM-dd").format(mon.getTime());
		  
		 model.addAttribute("toDay",toDay);
		 model.addAttribute("monthDate",monthDate);
		 model.addAttribute("userMax", strUserMax);
		 model.addAttribute("list5", lbList5);
		 model.addAttribute("todayBoard", nTodayBoard);
		 model.addAttribute("usertotal", nUserCount);
		 model.addAttribute("total5Board", nTotal5Board);
		 model.addAttribute("MasterBoardlist",lbMasterBoardlist );
		 model.addAttribute("boardtotal", nTotalBoard);
		 return "../../index";
	}

	// 게시글 내용클릭시 상세페이지 이동+상세페이지클릭시 조회수+1 
	@RequestMapping("/boardClick.do")
	public String boardClick(int bno, Model model) {
						
		Board b		                       = new Board(); // b인스턴스 생성
		b=boardService.boardClick(bno); // 상세페이지 클릭 시
		int nViewcheck                     = b.getBoardViewcheck() + 1; // 상세페이지 이동 성공시 조회수+1
		List<Map<String, Object>> filelist = boardService.selectFilelist(bno); // 게시글번호에 해당하는 파일번호
		List<Comment> com                  = commentService.commentkist(bno);// 게시글번호를가져가서 댓글전체내용출력
		  				
		b.setBno(b.getBno());  				
		b.setBoardViewcheck(nViewcheck); // b 인스턴스에 조회수 넣기
		boardService.boardView(b); // 게시글번호+1증가된 조회수 db에 넣기
		
		model.addAttribute("file", filelist);
		model.addAttribute("com", com);
		model.addAttribute("board", b);
		return "board/boarddetail";
	}

	// 추천하기 클릭시
	@RequestMapping("/good.do")
	public String good(int bno, Model model, Board board,HttpServletRequest request) {
		String strDatePicker1    = request.getParameter("datepicker1");
		String strDatePicker2    = request.getParameter("datepicker2");
		Board b                  = new Board(); // b인스턴스 생성
		int nBoardCodeNum        = Integer.parseInt(request.getParameter("boardCodeNum"));
		int nCurrentPage         = Integer.parseInt(request.getParameter("currentPage"));
		int nBoardRecommend      = b.getBoardRecommend(); // 게시글추천수=b인스턴스안에 게시글추천수
		 
		b.setBno(bno);
		b.setBoardRecommend(nBoardRecommend); // b 인스턴스 안에 게시글 추천수를 넣음
		int result               = boardService.boardgood(b); // 추천하기 클릭시 BoardRecommend가 +1 되는 비즈니스로직 수행

		if(result > 0) {
			return "redirect:/boardClick.do?bno="+bno+"&boardCodeNum="+nBoardCodeNum+"&currentPage=" + nCurrentPage+"&datepicker1="+strDatePicker1+"&datepicker2="+strDatePicker2;				
		}
		model.addAttribute("msg", "추천 하기 실패");
		model.addAttribute("url","boarddetail.jsp");
	return "common/msg";
		}

	// 게시글 수정 이동
	@RequestMapping("/updatemove.do")
	public String updatemove(int bno, Model model) {

		Board board                        = boardService.boardClick(bno); // bno값과 일치하는 데이터들을 가져오기위해
		List<Map<String, Object>> filelist = boardService.selectFilelist(bno);
		model.addAttribute("list", board);
		model.addAttribute("file", filelist);
		return "board/boardupdate";
	}

	

//댓글 삭제  댓글은 게시글에 종속되기떄문에 controller를 board에 작성
	@RequestMapping("/delcomment.do")
	public String delcomment(int commentNum, int bno, Model model,HttpServletRequest request) {

					int nResult          = commentService.delcomment(commentNum); // 댓글번호,게시글번호를 불러와서 댓글번호,게시글번호에 해당하는 내용 삭제
		     List<Comment> com           = commentService.commentkist(bno); // 댓글TB 전체목록 가져오는것
		           Board board           = boardService.boardClick(bno); // 게시글번호에 해당하는 내용 출력
		           int nCurrentPage      = Integer.parseInt(request.getParameter("currentPage"));
		           int nBoardCodeNum     = Integer.parseInt(request.getParameter("boardCodeNum"));
		           String strDatePicker1 = request.getParameter("datepicker1");
		           String strDatePicker2 = request.getParameter("datepicker2");
		           
		      if ( nResult > 0 ) { //Result값이 있다면 
		    	  	model.addAttribute("com", com);
		    	  	model.addAttribute("board", board);
			return "redirect:/boardClick.do?bno="+bno+"&boardCodeNum="+nBoardCodeNum+"&currentPage="+nCurrentPage+"&datepicker1="+strDatePicker1+"&datepicker2="+strDatePicker2;
		} //없다면 
		  	model.addAttribute("msg", "댓글 삭제 완료 ");
			model.addAttribute("url","boarddetail.jsp");
		return "common/msg";
}

	// 파일 다운로드
	@RequestMapping("/fileDown.do")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws IOException {
		// @RequestParam 으로 jsp에있는 map객체 (FILL_NUM)을 가져올수있다.
		// @request : jsp에서 서버로 요청
		// @response : 서버에서 jsp로 응답
	    Map<String, Object> resultMap    	 = boardService.selectFileInfo(map); // 첨부파일 조회
		String strStoredFileName             = (String) resultMap.get("FILE_RENAME");
		String strOriginalFileName           = (String) resultMap.get("FILE_ORINAME");
		byte fileByte[]                	     = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\mp\\file\\" + strStoredFileName)); // 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition","attachment; fileName=\"" + URLEncoder.encode(strOriginalFileName, "UTF-8") + "\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush(); // 데이터를 비워줌
		response.getOutputStream().close(); // 닫아줌VVVV
		}
	}
