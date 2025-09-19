package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Books;


@WebServlet("/ClientHome")
public class ClientHomeServlet extends HttpServlet {
	private static List<Books> booksList = new ArrayList<>();
	private static int idCounter=1;
	
    @Override
    public void init() {
    	//Dữ liệu giả
    	booksList.add(new Books(idCounter++, "Sách A", "Tác giả A", 50000, "linkA.jpg"));
        booksList.add(new Books(idCounter++, "Sách B", "Tác giả B", 60000, "linkB.jpg"));
    }
  
    //Index
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String idList = request.getParameter("id");
		String action = request.getParameter("action");
		
		if(action == null) action = "list";
		
		switch(action) {
		//Xem chi tiết
		case "detail":
			int idDetail = Integer.parseInt(idList);
			Books detailBooks = findById(idDetail);
			request.setAttribute("books", detailBooks);
			request.getRequestDispatcher("detail-client.jsp").forward(request, response);
			break;
		default:
			request.setAttribute("booksList",booksList);
			request.getRequestDispatcher("index.jsp").forward(request,response);
			break;
		}
	}

	private Books findById(int id) {
		for(Books n: booksList) {
			if(n.getId()==id)
				return n;
		}
		return null;
	}
	

}
