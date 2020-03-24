package org.core.util;

import java.util.ArrayList;

import org.core.board.model.dao.BoardDao;
import org.core.board.model.vo.Board;
import org.core.board.model.vo.BoardPageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("PagingSearchUtil")
public class PagingSearchUtil {
	@Autowired

	@Qualifier("boardDao")
	BoardDao boardDao;

	public BoardPageData searchpaging(Board board, int currentPage) {
		
		//select값 selectPage값 currentPage값 strBoardPath값 search값을 컨트롤러에서받아야함

		int nNumPage         = Integer.parseInt(board.getSelectPage());
		int nStart           = (currentPage - 1) * nNumPage + 1;
		int nEnd             = currentPage * nNumPage;
		board.setEnd(nEnd);
		board.setStart(nStart);
		
		ArrayList<Board> list = (ArrayList<Board>) boardDao.searchBoard(board);
		int nTotalCount       = boardDao.searchTotalBoard(board);
		int nTotalPage        = 0;

		if ( nTotalCount % nNumPage == 0 ) {
				nTotalPage = nTotalCount / nNumPage;
		} else {
				nTotalPage = nTotalCount / nNumPage + 1;
		}
		
		int nPageNaviSize     = 5;
		int nStartNavi        = ((currentPage - 1) / nPageNaviSize) * nPageNaviSize + 1;
		int nEndNavi          = nStartNavi + nPageNaviSize - 1;
		         
		if ( nEndNavi > nTotalPage ) {
				nEndNavi = nTotalPage;
			}
		StringBuilder sb      = new StringBuilder();
		String strBoardPath   = board.getPath();

		if ( nStartNavi != 1 ) { // 시작버튼이 1이 아닐때 처음페이지로 이동 < (3)
				sb.append("<a href='"+strBoardPath+"?currentPage=" + (1) +"&boardCodeNum="+board.getBoardCodeNum()+ "&selectpage=" + board.getSelectPage()
					+ "&select=" + board.getSelect() + "&search=" + board.getSearch() +"&datepicker1="+board.getDatepick1()+"&datepicker2="+board.getDatepick2()+ "'> < </a>");
		} 
		
		if ( nStartNavi != 1 ) { // 시작버튼이 1이 아닐때 전 페이지 시작네비-1로 이동 <(2)  ex) 6 7 8 9 10 이면 5
			sb.append("<a href='"+strBoardPath+"?currentPage=" + (nStartNavi - 1) +"&boardCodeNum="+board.getBoardCodeNum()+ "&selectpage=" + board.getSelectPage()
					+ "&select=" + board.getSelect() + "&search=" + board.getSearch() +"&search=" + board.getSearch() +"&datepicker1="+board.getDatepick1()+"&datepicker2="+board.getDatepick2()+ "'> < </a>");
		}
		
		if ( nStartNavi != 1 ) { // 시작버튼이 1이 아닐때 <(1) 클릭시 한칸씩 이동
			sb.append("<a href='"+strBoardPath+"?currentPage=" + (currentPage - 1) +"&boardCodeNum="+board.getBoardCodeNum()+ "&selectpage=" + board.getSelectPage()
							+ "&select=" + board.getSelect() + "&search=" + board.getSearch() +"&datepicker1="+board.getDatepick1()+"&datepicker2="+board.getDatepick2()+ "'> < </a>");
		}
		
		if ( currentPage < 6 && currentPage != 1 ) { // 현재페이지가 6보다작고 현재페이지가 1이 아닐때  (첫 페이지 네비일때 <(3)누르면 1로 이동(시작페이지)
			sb.append("<a href='"+strBoardPath+"?currentPage=" + (1) +"&boardCodeNum="+board.getBoardCodeNum()+ "&selectpage=" + board.getSelectPage()
					+ "&select=" + board.getSelect() + "&search=" + board.getSearch() +"&datepicker1="+board.getDatepick1()+"&datepicker2="+board.getDatepick2()+ "'> < </a>");
		}
		
		if ( currentPage < 6 && currentPage != 1 ) { // 현재페이지가 6보다 작고 현재페이지가 1이아닐때 ( 첫페이지 네비일때 <(1) 누르면 1칸씩 이동)
			sb.append("<a href='"+strBoardPath+"?currentPage=" + (currentPage - 1) + "&boardCodeNum="+board.getBoardCodeNum()+"&selectpage=" + board.getSelectPage()
							+ "&select=" + board.getSelect() + "&search=" + board.getSearch() +"&datepicker1="+board.getDatepick1()+"&datepicker2="+board.getDatepick2()+ "'> < </a>");
		}

		for ( int i = nStartNavi; i <= nEndNavi; i++ ) { // i=시작네비 i=종료네비까지 1씩 증가
			if (i != currentPage) { // 시작네비가 현재페이지와 다르면
				sb.append("<a href='"+strBoardPath+"?currentPage=" + i + "&boardCodeNum="+board.getBoardCodeNum()+"&selectpage=" + board.getSelectPage()
						+ "&select=" + board.getSelect() + "&search=" + board.getSearch() +"&datepicker1="+board.getDatepick1()+"&datepicker2="+board.getDatepick2()+ "'>" + i + "</a>");
			} else {
				sb.append("<span>" + i + "</span>"); 
			}
		}
		
		if ( nEndNavi != nTotalPage || currentPage < nTotalPage ) { // 종료네비와 마지막페이지가 같지않거나 현재페이지가 마지막페이지보다 작으면 >(1) 1칸식 증가
			sb.append("<a href='"+strBoardPath+"?currentPage=" + (currentPage + 1) +"&boardCodeNum="+board.getBoardCodeNum()+ "&selectpage=" + board.getSelectPage()
							+ "&select=" + board.getSelect() + "&search=" + board.getSearch() +"&datepicker1="+board.getDatepick1()+"&datepicker2="+board.getDatepick2()+ "'> > </a>");
			// 종료네비가 마지막페이지가 아니면 >[다음]버튼 출력
			// 1칸씩이동 >
		}
		
		if ( nEndNavi != nTotalPage ) { // 종료네비와 마지막페이지가 같지않으면 >(2) 다음네비페이지+1 ex) 1 2 3 4 5이면 6
			sb.append("<a href='"+strBoardPath+"?currentPage=" + (nEndNavi + 1) +"&boardCodeNum="+board.getBoardCodeNum()+ "&selectpage=" + board.getSelectPage()
					+ "&select=" + board.getSelect() + "&search=" + board.getSearch() +"&datepicker1="+board.getDatepick1()+"&datepicker2="+board.getDatepick2()+ "'> > </a>");
			// 종료네비가 마지막페이지가 아니면 >[다음]버튼 출력
			// 마지막 페이지로 이동
		}
		
		if ( nEndNavi != nTotalPage && currentPage != nTotalPage ) { // 종료네비오하 마지막페이지가 같지않으며 현재페이지가 최종페이지보다작으면 >(3) 마지막페이지로 이동
			sb.append("<a href='"+strBoardPath+"?currentPage=" + (nTotalPage) + "&boardCodeNum="+board.getBoardCodeNum()+"&selectpage=" + board.getSelectPage()
					+ "&select=" + board.getSelect() + "&search=" + board.getSearch() +"&datepicker1="+board.getDatepick1()+"&datepicker2="+board.getDatepick2()+ "'> > </a>");
			// 종료네비가 마지막페이지가 아니면 >[다음]버튼 출력
			// 10칸 씩 이동
		}
		
		BoardPageData bpd = new BoardPageData(list, sb.toString());
		
	    return bpd;
      }
   }
