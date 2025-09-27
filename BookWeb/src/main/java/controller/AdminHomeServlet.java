package controller;

import dao.BookDAO;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminHome")
public class AdminHomeServlet extends HttpServlet {

    private BookDAO bookDAO;

    @Override
    public void init() {
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "create":
                    req.getRequestDispatcher("form.jsp").forward(req, resp);
                    break;

                case "edit": {
                    String idStr = req.getParameter("id");
                    Book editBook = bookDAO.getBookById(Integer.parseInt(idStr));
                    req.setAttribute("book", editBook);
                    req.getRequestDispatcher("form.jsp").forward(req, resp);
                    break;
                }

                case "delete": {
                    String idStr = req.getParameter("id");
                    bookDAO.deleteBook(Integer.parseInt(idStr));
                    resp.sendRedirect("adminHome"); // ✅ sửa lại
                    break;
                }

                case "detail": {
                    String idStr = req.getParameter("id");
                    Book detailBook = bookDAO.getBookById(Integer.parseInt(idStr));
                    req.setAttribute("book", detailBook);
                    req.getRequestDispatcher("detail.jsp").forward(req, resp);
                    break;
                }

                default: // list
                    List<Book> books = bookDAO.getAllBooks();
                    req.setAttribute("bookList", books);
                    req.getRequestDispatcher("list.jsp").forward(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String priceStr = req.getParameter("price");
        String imagePath = req.getParameter("imagePath");

        int price = (priceStr != null && !priceStr.isEmpty()) ? Integer.parseInt(priceStr) : 0;

        Book book = new Book(id, name, author, price, imagePath);

        if (id == null || id.isEmpty()) {
            bookDAO.addBook(book);
        } else {
            bookDAO.updateBook(book);
        }

        resp.sendRedirect("adminHome"); // ✅ sửa lại
    }
}

