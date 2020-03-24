package org.core.user.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.core.board.model.service.BoardService;
import org.core.board.model.vo.Board;
import org.core.board.model.vo.MasterBoard;
import org.core.user.model.service.UserService;
import org.core.user.model.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import net.sf.json.JSONObject;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@Autowired
	@Qualifier("boardService")
	BoardService boardService;
	
	//로그인
	@RequestMapping("/login.do")
	public String loginUser(HttpServletRequest request, Model model, Board board,MasterBoard mboard) {

		  User u                      = new User();
		  String strUserId            = request.getParameter("userId");
	      String strUserPassword 	  = request.getParameter("userPassword");
	      u.setUserId(strUserId);
	      u.setUserPassword(strUserPassword); 
	      User user 		  		  = userService.loginUser(u); // 입력한 id,pw값을 u에 넣어서 비즈니스로직 처리
	      int todayBoard 	  		  = boardService.todayBoard(); // 오늘 등록한 게시물 갯수
	      int total5Board 	  		  = boardService.total5Board(); // 5일 내 등록한 게시물 갯수
	      String userMax 	 		  = userService.userMax(); // 최다 게시글 등록자 명
		  int userCount  	  		  = userService.userCount(); // 총 회원수 카운트
		  int totalBoard      		  = boardService.totalBoardMax();// 총 게시물 수
		  List<Board> list5   		  = boardService.boardList5(board);// 5개게시물가져오기
		  HttpSession session         = request.getSession(); // 로그인 세션만들기
		  								session.setAttribute("user", user); // user라는 로그인세션
	
		  List<Board> MasterBoardlist = boardService.MasterBoardlist();
		  
		  Date day = new Date();        
		  SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		  String toDay = date.format(day);
		  
		  Calendar mon = Calendar.getInstance();
		  mon.add(Calendar.MONTH , -1);
		  String monthDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(mon.getTime());
		 
		  
		if (user != null) { 
			model.addAttribute("toDay",toDay);
			model.addAttribute("monthDate",monthDate);
			model.addAttribute("MasterBoardlist",MasterBoardlist );
			model.addAttribute("total5Board", total5Board);
			model.addAttribute("userMax", userMax);
			model.addAttribute("todayBoard", todayBoard);
			model.addAttribute("u", u);
			model.addAttribute("list5", list5);
			model.addAttribute("boardtotal", totalBoard);
			model.addAttribute("usertotal", userCount);
			return "../../index"; // index.jsp로
		}
		model.addAttribute("msg", "로그인 실패 ID or Pw를 확인하세요");
		model.addAttribute("url","index.jsp");
	return "common/msg"; // 로그인실패 문구로
}

//로그아웃
	@RequestMapping("/logout.do")
	public String logoutUser(HttpSession session) {
			session.invalidate(); // 세션 무효화 소멸x
		return "redirect:/"; // index.jsp로
	}

//회원가입창으로 이동
	@RequestMapping("/insertUser.do")
	public String insertUser() {
		return "user/insertuser";
	}

//회원가입 완료버튼 누를시
	@RequestMapping("/insertUserData.do")
	public String InsertUserData(HttpServletRequest request) {

	  User user = new User(); 
      String strUserId       = request.getParameter("userId"); 
	  String strUserPassword = request.getParameter("userPassword"); 
	  String strUserName     = request.getParameter("userName");
	  
	  user.setUserName(strUserName);
	  user.setUserId(strUserId);
	  user.setUserPassword(strUserPassword);
		 
	  int resultt = userService.idChk(user);// 아이디 중복체크
	  try {
	      if (resultt == 1) {
	      return "user/insertuser";
	      } else {
	     userService.InsertUserData(user);
	      }
	     } catch (Exception e) {
	      throw new RuntimeException();
	     }
	     return "redirect:/"; // index.jsp 로이동
	}
//회원 탈퇴
	@RequestMapping("/deleteuser.do")
	public String deleteuser(HttpServletRequest request, Model model, HttpSession session) {

		 User user        = (User) request.getSession().getAttribute("user"); // 세션값 가져오기
		 String strUserId = user.getUserId(); // 현재 로그인한 userId
		 int result       = userService.deleteuser(strUserId); // 로그인한 userId를 매개변수로 비즈니스로직 수행

		if (result > 0) { // 가져온값이 0 이상이면
			session.invalidate(); // 회원탈퇴 후 세션값도 종료
			return "redirect:/"; // index.jsp로 이동
		} else {
			return "redirect:/";
		}
	}

//id중복체크
	@ResponseBody
	@RequestMapping("/idChk.do")
	public String idChk(HttpServletRequest request) throws Exception {
		
		String strUserId = request.getParameter("userId");
		User user = new User();
		user.setUserId(strUserId);

		int result = userService.idChk(user);

	   JSONObject obj = new JSONObject();
			if (result != 0) {
					obj.put("result", "1");
				} else {
					obj.put("result", "0");
				}
			return new Gson().toJson(obj);
		}
	}
