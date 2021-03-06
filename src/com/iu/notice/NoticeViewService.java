package com.iu.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.board.BoardDTO;

public class NoticeViewService implements Action {

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
		NoticeDAO noticeDAO=new NoticeDAO();
		try {
			boardDTO=noticeDAO.selectOne(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(boardDTO!=null) {
			request.setAttribute("view", boardDTO);
			actionForward.setPath("../WEB-INF/view/board/boardView.jsp");
			request.setAttribute("board","notice");
		}else {
			request.setAttribute("message", "글 없음");
			request.setAttribute("path", "./noticeList.notice");
			actionForward.setPath("../WEB-INF/view/common/result.jsp");
		}
		
		actionForward.setCheck(true);
		
		
		return actionForward;
	}

}
