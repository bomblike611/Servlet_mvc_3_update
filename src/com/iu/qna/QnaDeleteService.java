package com.iu.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.notice.NoticeDAO;

public class QnaDeleteService implements Action {

	@Override
	public ActionForward doProcess(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward=new ActionForward();
		
		QnaDAO qnaDAO=new QnaDAO();
		
		int num=Integer.parseInt(request.getParameter("num"));
		int result=0;
		try {
			result=qnaDAO.delete(num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/view/common/result.jsp");
		if(result>0) {
			request.setAttribute("message", "Success");
		}else {
			request.setAttribute("message", "Fail");
		}
		request.setAttribute("path","./qnaList.qna");
		
		
		return actionForward;
	}

}
