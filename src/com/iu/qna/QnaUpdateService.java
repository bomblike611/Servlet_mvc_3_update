package com.iu.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.board.BoardDTO;
import com.iu.notice.NoticeDAO;

public class QnaUpdateService implements Action {

	@Override
	public ActionForward doProcess(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward=new ActionForward();
		String method=request.getMethod();
		QnaDAO qnaDAO=new QnaDAO();
		BoardDTO boardDTO=null;
		int num=0;
		
		try {
			num=Integer.parseInt(request.getParameter("num"));
			boardDTO=qnaDAO.selectOne(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(method.equals("GET")) {
			if(boardDTO!=null) {
			request.setAttribute("board", "qna");
			request.setAttribute("a", boardDTO);
			actionForward.setPath("../WEB-INF/view/board/boardUpdate.jsp");
			}else {
				request.setAttribute("message", "Fail");
				request.setAttribute("path", "./qnaList.qna");
				actionForward.setPath("../WEB-INF/view/common/result.jsp");
			}
			actionForward.setCheck(true);
		}else {
			BoardDTO boardDTO2=new BoardDTO();
			int result=0;
			try {
				boardDTO2.setTitle(request.getParameter("title"));
				boardDTO2.setNum(num);
				boardDTO2.setWriter(request.getParameter("writer"));
				boardDTO2.setContents(request.getParameter("contents"));
				result=qnaDAO.update(boardDTO2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("board", "qna");
			request.setAttribute("path", "./qnaList.qna");
			
			if(result>0) {
				request.setAttribute("message", "업데이트 성공");
			}else {
				request.setAttribute("message", "업데이트 실패");
			}
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/common/result.jsp");
			
		}
		
		
		return actionForward;
	}

}
