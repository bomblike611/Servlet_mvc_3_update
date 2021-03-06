package com.iu.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.board.BoardDTO;
import com.iu.util.MakePage;
import com.iu.util.MakeRow;
import com.iu.util.Pageing;

public class QnaListService implements Action {

	@Override
	public ActionForward doProcess(HttpServletRequest request, HttpServletResponse response) {
		 ActionForward actionForward=new ActionForward();
		 
		 int curPage=1;
		 
		 try {
			 curPage=Integer.parseInt(request.getParameter("curPage"));
		 }catch (Exception e) {
			// TODO: handle exception
		}
		 MakeRow makeRow=new MakeRow();
		 makeRow.setKind(request.getParameter("kind"));
		 makeRow.setSearch(request.getParameter("search"));
		 QnaDAO qnaDAO=new QnaDAO();
		 int totalCount;
		try {
			totalCount = qnaDAO.getTotalCount(makeRow);
			MakePage makePage=new MakePage(curPage, totalCount);
			makeRow=makePage.getMakeRow(makeRow);
			List<BoardDTO> ar=qnaDAO.selectList(makeRow);
			
			
			Pageing pageing=makePage.pageing();
			request.setAttribute("list", ar);
			request.setAttribute("page", pageing);
			request.setAttribute("board","qna");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 actionForward.setCheck(true);
		 actionForward.setPath("../WEB-INF/view/board/boardList.jsp");
		 
		 
		 
		return actionForward;
	}

}
