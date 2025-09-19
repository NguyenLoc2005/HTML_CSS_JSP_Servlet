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

@WebServlet("/books")
public class BooksServlet extends HttpServlet {

    private static List<Books> booksList = new ArrayList<>();
    private static int idCounter = 1;

    @Override
    public void init() {
    	//Dữ liệu giả định
        booksList.add(new Books(idCounter++, "Sách A", "Tac gia A", 50000, "linkA.jpg"));
        booksList.add(new Books(idCounter++, "Sách B", "Tac gia B", 60000, "linkB.jpg"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            // Mặc định hiển thị list
            request.setAttribute("booksList", booksList);
            request.getRequestDispatcher("list.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "create":
                request.getRequestDispatcher("form.jsp").forward(request, response);
                break;
                
            case "edit":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Books booksEdit = findById(idEdit);
                request.setAttribute("books", booksEdit);
                request.getRequestDispatcher("form.jsp").forward(request, response);
                break;
                
            case "detail":
                int idDetail = Integer.parseInt(request.getParameter("id"));
                Books booksDetail = findById(idDetail);
                request.setAttribute("books", booksDetail);
                request.getRequestDispatcher("detail.jsp").forward(request, response);
                break;
                
            case "delete":
                int idDelete = Integer.parseInt(request.getParameter("id"));
                Books booksDelete = findById(idDelete);
                if (booksDelete != null) booksList.remove(booksDelete);
                response.sendRedirect("books");
                break;
                
            default:
                request.setAttribute("booksList", booksList);
                request.getRequestDispatcher("list.jsp").forward(request, response);
                break;
        }
    }

    
    //List
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        int price = Integer.parseInt(request.getParameter("price"));
        String imagePath = request.getParameter("imagePath");

        if (idStr == null || idStr.isEmpty()) {
            // Thêm mới
            Books newBook = new Books(idCounter++, name, author, price, imagePath);
            booksList.add(newBook);
        } else {
            // Cập nhật
            int id = Integer.parseInt(idStr);
            Books booksUpdate = findById(id);
            if (booksUpdate != null) {
                booksUpdate.setName(name);
                booksUpdate.setAuthor(author);
                booksUpdate.setPrice(price);
                booksUpdate.setImagePath(imagePath);
            }
        }
        response.sendRedirect("books");
    }

    private Books findById(int id) {
        for (Books n : booksList) {
            if (n.getId() == id) return n;
        }
        return null;
    }
}
