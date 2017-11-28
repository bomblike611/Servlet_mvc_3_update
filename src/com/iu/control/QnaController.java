package com.iu.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;

/**
 * Servlet implementation class QnaController
 */
@WebServlet("/QnaController")
public class QnaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private Map<String, Object> command;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaController() {
        super();
        // TODO Auto-generated constructor stub
    }
  //1.command 객체 만들기
  //2.property파일의 경로명 가져오기
  //3.property 파일 이름 가져 오기
  //4.파일의 내용을 읽어올 준비
  //5.파일의 내용을 읽어서 파싱 한 다음 저장
  //6.prop에서 키를 읽어서 value를 찾아서 객체 생성
    @Override
    public void init(ServletConfig config) throws ServletException {
    	command=new HashMap<>();
    	String filePath=config.getServletContext().getRealPath("WEB-INF/property");
    	String fileName=config.getInitParameter("property");
    	
    	File file=new File(filePath, fileName);
    	FileInputStream fi=null;
    	Properties prop=new Properties();
    	
    	try {
    		fi=new FileInputStream(file);
			prop.load(fi);
			
			Iterator<Object> it=prop.keySet().iterator();
			while(it.hasNext()) {
				String key=(String)it.next();
				String value=(String)prop.get(key);
				Class ins=Class.forName(value);
				Object obj=ins.newInstance();
				command.put(key, obj);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fi.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String path=request.getServletPath();
	Action action=null;
	ActionForward actionForward=null;
	
	action=(Action)command.get(path);
	actionForward=action.doProcess(request, response);
	
	if(actionForward.isCheck()) {
		RequestDispatcher view=request.getRequestDispatcher(actionForward.getPath());
		view.forward(request, response);
	}else {
		response.sendRedirect(actionForward.getPath());
	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
