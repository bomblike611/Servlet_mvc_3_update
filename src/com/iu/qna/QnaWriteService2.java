package com.iu.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;

public class QnaWriteService2 implements Action {

	@Override
	public ActionForward doProcess(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward= new ActionForward();
		String method=request.getMethod();
		QnaDTO qnaDTO=null;
		QnaDAO qnaDAO=new QnaDAO();
		if(method.equals("POST")) {
			qnaDTO=new QnaDTO();
			int num=0;
			int result=0;
			try {
				num = qnaDAO.getNum();
				qnaDTO.setNum(num);
				qnaDTO.setTitle(request.getParameter("title"));
				qnaDTO.setWriter(request.getParameter("writer"));
				qnaDTO.setContents(request.getParameter("contents"));
				result=qnaDAO.insert(qnaDTO);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(result>0) {
				actionForward.setCheck(false);
				actionForward.setPath("./qnaList.qna");
			}else {
				request.setAttribute("message", "입력 실패");
				request.setAttribute("path", "./qnaList.qna");
				actionForward.setCheck(true);
				actionForward.setPath("../WEB-INF/view/common/result.jsp");
			}
			
			
		}else {
			request.setAttribute("board", "qna");
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/board/boardWrite.jsp");
		}
		
		
		
		
		return actionForward;
	}

}
