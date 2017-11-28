package com.iu.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;

public class NoticeWriteService implements Action {

	@Override
	public ActionForward doProcess(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String method=request.getMethod();
		int result=0;
		
		NoticeDTO noticeDTO=null;
		NoticeDAO noticeDAO=new NoticeDAO();
		if(method.equals("POST")) {
			
		try {
			noticeDTO=new NoticeDTO();
			int num=noticeDAO.getNum();
			noticeDTO.setNum(num);
			noticeDTO.setWriter(request.getParameter("writer"));
			noticeDTO.setTitle(request.getParameter("title"));
			noticeDTO.setContents(request.getParameter("contents"));
			result=noticeDAO.insert(noticeDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result>0) {
			actionForward.setCheck(false);
			actionForward.setPath("./noticeList.notice");
		}else {
			request.setAttribute("message", "입력 실패");
			request.setAttribute("path", "./noticeList.notice");
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/common/result.jsp");
		}
		}else {
			request.setAttribute("board", "notice");
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/board/boardWrite.jsp");
		}
		
		
		
		return actionForward;
	}

}
