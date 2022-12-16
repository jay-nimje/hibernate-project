package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UserDao;
import Modal.User;



/**
 * Servlet implementation class actioncontroller
 */
@WebServlet("/actioncontroller")
public class actioncontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public actioncontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		if (action.equalsIgnoreCase("register")) {
			User u = new User();
			u.setName(request.getParameter("name"));
			u.setContact(Long.parseLong(request.getParameter("contact")));
			u.setAddress(request.getParameter("address"));
			u.setEmail(request.getParameter("email"));
			u.setPassword(request.getParameter("password"));
			System.out.println(u);
			new UserDao().insertUser(u);
			request.setAttribute("msg", "data inserted");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		else if (action.equalsIgnoreCase("login")) {
			User u = new User();
			u.setEmail(request.getParameter("email"));
			u.setPassword(request.getParameter("password"));
			User u1 = new UserDao().logi(u);
			System.out.println(u1);
			if (u1 == null) {
				request.setAttribute("validata", "email or password is inccorect");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else
			{
				HttpSession session = request.getSession();
				session.setAttribute("data", u1);
				System.out.println("login done");
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("edit"))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			User u = new User();
			request.setAttribute("data", u);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			
		}
		else if(action.equalsIgnoreCase("update")) {
			User u = new User();
			u.setId(Integer.parseInt(request.getParameter("id")));
			u.setName(request.getParameter("name"));
			u.setContact(Long.parseLong(request.getParameter("contact")));
			u.setAddress(request.getParameter("address"));
			u.setEmail(request.getParameter("email"));
			u.setPassword(request.getParameter("password"));
			User u1 = new UserDao().updateUser(u);
			HttpSession session = request.getSession();
			session.setAttribute("data", u1);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		else if (action.equalsIgnoreCase("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			User u = new UserDao().deleteUser(id);
			HttpSession session = request.getSession();
			session.setAttribute("data", u);
			request.getRequestDispatcher("home.jsp").forward(request, response);
			
		}
		
		
	}

}
