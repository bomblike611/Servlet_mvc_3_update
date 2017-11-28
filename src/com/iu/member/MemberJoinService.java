package com.iu.member;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.student.StudentDAO;
import com.iu.student.StudentDTO;
import com.iu.teacher.TeacherDAO;
import com.iu.teacher.TeacherDTO;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

public class MemberJoinService implements Action {

	@Override
	public ActionForward doProcess(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward=new ActionForward();
		String method=request.getMethod();

		if(method.equals("GET")) {
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/view/member/memberJoin.jsp");
		}else {
			MemberDAO memberDAO=new MemberDAO();
			MemberDTO memberDTO=new MemberDTO();
			StudentDTO studentDTO=null;
			TeacherDTO teacherDTO=null;
			String id=request.getParameter("id");

			memberDTO.setAge(Integer.parseInt(request.getParameter("age")));
			memberDTO.setEmail(request.getParameter("email"));
			memberDTO.setId(id);
			memberDTO.setPw(request.getParameter("pw"));
			memberDTO.setName(request.getParameter("name"));
			memberDTO.setPhone(request.getParameter("phone"));
			memberDTO.setJob(request.getParameter("job"));
			int result=0;
			try {
				result=memberDAO.join(memberDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(result>0) {
				if(memberDTO.getJob().equals("T")) {
					teacherDTO=new TeacherDTO();
					TeacherDAO teacherDAO=new TeacherDAO();
					teacherDTO.setId(id);
					teacherDTO.setBirth(Date.valueOf(request.getParameter("birth")));
					teacherDTO.setSalary(Integer.parseInt(request.getParameter("salary")));
					teacherDTO.setSubject(request.getParameter("subject"));
					
					try {
						result=teacherDAO.join(teacherDTO);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					studentDTO=new StudentDTO();
					StudentDAO studentDAO=new StudentDAO();
					studentDTO.setId(id);
					studentDTO.setAddr(request.getParameter("addr"));
					studentDTO.setGrade(Integer.parseInt(request.getParameter("grade")));
					studentDTO.setTid(request.getParameter("tid"));
					try {
						result=studentDAO.join(studentDTO);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(result>0) {
					request.setAttribute("message", "회원가입 성공");
					request.setAttribute("path", "../index.jsp");
					actionForward.setCheck(true);
					actionForward.setPath("../WEB-INF/view/common/result.jsp");
				}
				
			}
			
			if(result<0){
				request.setAttribute("message", "회원가입 실패");
				request.setAttribute("path", "../index.jsp");
				actionForward.setCheck(true);
				actionForward.setPath("../WEB-INF/view/common/result.jsp");
			}//result else
		}

		return actionForward;
	}

}
