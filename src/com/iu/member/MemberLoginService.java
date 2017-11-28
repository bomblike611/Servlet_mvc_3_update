package com.iu.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.teacher.TeacherDAO;

public class MemberLoginService implements Action {

	@Override
	public ActionForward doProcess(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward=new ActionForward();
		String method=request.getMethod();
		
		if(method.equals("GET")) {
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/member/memberLogin.jsp");
		}else {
			MemberDAO memberDAO=new MemberDAO();
			MemberDTO mDTO=new MemberDTO();
			MemberDTO memberDTO=null;
			mDTO.setId(request.getParameter("id"));
			mDTO.setPw(request.getParameter("pw"));
			mDTO.setJob(request.getParameter("job"));
			try {
				memberDTO=memberDAO.login(mDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			actionForward.setCheck(true);
			if(memberDTO !=null) {
			request.getSession().setAttribute("member", memberDTO);
			actionForward.setPath("../index.jsp");
			}else {
				request.setAttribute("message", "실패");
				request.setAttribute("path", "../index.jsp");
				actionForward.setCheck(true);
				actionForward.setPath("../WEB-INF/view/common/result.jsp");
			}
			
			
		}
		
		
		
		return actionForward;
	}

}
