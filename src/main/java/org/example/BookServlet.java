package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize data source (use your connection pool here)
        dataSource = MyDataSourceFactory.getDataSource();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = new ArrayList<>();
        try {
            // Connect to the database
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getLong("id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("books", books);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addBook(request, response);
                    break;
                case "edit":
                    editBook(request, response);
                    break;
                case "delete":
                    deleteBook(request, response);
                    break;
            }
        }
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        try {
            // Connect to the database
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO books (title, author) VALUES (?, ?)")) {
                statement.setString(1, title);
                statement.setString(2, author);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/books");
    }

    private void editBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        try {
            // Connect to the database
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("UPDATE books SET title=?, author=? WHERE id=?")) {
                statement.setString(1, title);
                statement.setString(2, author);
                statement.setLong(3, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/books");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        try {
            // Connect to the database
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE id=?")) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/books");
    }
}
