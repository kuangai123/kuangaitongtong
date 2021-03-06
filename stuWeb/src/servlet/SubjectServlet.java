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

public class SubjectServlet extends HttpServlet {
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
			show(request, response);
		} else if (type.equals("showAdd")) {// 新增
			showAdd(request, response);
		} else if (type.equals("add")) {
			add(request, response);
		} else if (type.equals("showModify")) {
			showModify(request, response);
		}
		else if (type.equals("modify")) {
			modify(request, response);
		} else if (type.equals("delete")) {
			delete(request, response);
		} else if (type.equals("search")) {
			search(request, response);
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {

		Subject condition = new Subject();
		String name = request.getParameter("name");
		if (!"".equals(name)) {
			condition.setName(name);
		}

		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int max = subDao.searchCount(condition);

		int yeNum = 2;
		int yeMa = 5;
		Pagination pagination = new Pagination(ye, max, yeNum, yeMa);
		ye = pagination.getYe();

		int begin = (ye - 1) * yeNum;

		List<Subject> list = subDao.searchByCondition(condition, begin, yeNum);

		request.setAttribute("p", pagination);
		request.setAttribute("condition", condition);
		request.setAttribute("subs", list);
		try {
			request.getRequestDispatcher("WEB-INF/subject/list.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameter("selectId").split(",");// selectId是选中
		boolean flag = false;
		for (int i = 0; i < ids.length; i++) {
			flag = subDao.delete(Integer.parseInt(ids[i]));
			if (flag == false) {
				break;
			}
		}

		if (flag) {

			try {
				response.sendRedirect("sub");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			Subject sub = new Subject();
			sub.setId(id);
			sub.setName(name);
		
			boolean flag = subDao.modify(sub);
			if (flag) {

				response.sendRedirect("sub");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showModify(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String ids[] = request.getParameter("selectId").split(",");
			Subject sub = subDao.searchById(Integer.parseInt(ids[0]));
			request.setAttribute("sub", sub);
			request.getRequestDispatcher("WEB-INF/subject/modify.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			Subject sub = new Subject();
			sub.setName(name);
	
			boolean flag = subDao.add(sub);
			if (flag) {

				response.sendRedirect("sub");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/subject/add.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void show(HttpServletRequest request, HttpServletResponse response) {
		try {

			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			int max = subDao.searchCount();

			int yeNum = 2;
			int yeMa = 5;
			Pagination pagination = new Pagination(ye, max, yeNum, yeMa);
			ye = pagination.getYe();

			int begin = (ye - 1) * yeNum;
			List<Subject> list = subDao.searchByBegin(begin, yeNum);
			request.setAttribute("subs", list);
			request.setAttribute("p", pagination);

			request.getRequestDispatcher("WEB-INF/subject/list.jsp").forward(
					request, response);
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
