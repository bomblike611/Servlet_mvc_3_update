package com.iu.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.board.BoardDTO;
import com.iu.notice.NoticeDAO;

public class QnaViewService implements Action {

	@Override
	public ActionForward doProcess(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward=new ActionForward();
		int num=0;
		BoardDTO boardDTO=null;
		try {
		num=Integer.parseInt(request.getParameter("num"));
		}catch (Exception e) {
			// TODO: handle exception
		}
		QnaDAO qnaDAO=new QnaDAO();
		try {
			boardDTO=qnaDAO.selectOne(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(boardDTO!=null) {
			request.setAttribute("view", boardDTO);
			actionForward.setPath("../WEB-INF/view/board/boardView.jsp");
			request.setAttribute("board","qna");
		}else {
			request.setAttribute("message", "글 없음");
			request.setAttribute("path", "./qnaList.qna");
			actionForward.setPath("../WEB-INF/view/common/result.jsp");
		}
		
		actionForward.setCheck(true);
		
		
		return actionForward;
	}

}
