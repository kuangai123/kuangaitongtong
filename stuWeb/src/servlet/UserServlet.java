package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.print.DocFlavor.READER;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Pagination;
import dao.SubjectDao;
import entity.Subject;

public class UserServlet extends HttpServlet {
	SubjectDao subDao = new SubjectDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String type = request.getParameter("type");
		if (type == null) {
			showLogin(request, response);
		} else if (type.equals("showRegister")) {// ÏÔÊ¾×¢²áÒ³Ãæ
			showRegister(request, response);
		} else if (type.equals("doRegister")) {// ×¢²á
			doRegister(request, response);
		} else if (type.equals("showLogin")) {// ÏÔÊ¾µÇÂ¼Ò³Ãæ
			showLogin(request, response);
		} else if (type.equals("doLogin")) {// µÇÂ¼
			doLogin(request, response);

		}
	}

	private void doRegister(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}




	private void showRegister(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/user/register.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	
	
	
	
	
	


	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) {
	
		
	}




	private void showLogin(HttpServletRequest request,
			HttpServletResponse response) {
	try {
		request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}








	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
