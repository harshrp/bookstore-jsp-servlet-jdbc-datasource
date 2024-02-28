<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="org.example.Book" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Book List</title>
</head>
<body>
    <h1>Book List</h1>
    <table class="book-table">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Actions</th>
        </tr>
        <%
            List<Book> books = (List<Book>) request.getAttribute("books");
            for (Book book : books) {
        %>
        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td>
                <form action="books" method="POST">
                    <input type="hidden" name="id" value="<%= book.getId() %>">
                    <input type="hidden" name="action" value="edit">
                    <input type="submit" value="Edit">
                </form>
                <form action="books" method="POST">
                    <input type="hidden" name="id" value="<%= book.getId() %>">
                    <input type="hidden" name="action" value="delete">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <h2>Add Book</h2>
    <form action="books" method="POST" class="book-form">
        <label>Title:</label>
        <input type="text" name="title" required>
        <label>Author:</label>
        <input type="text" name="author" required>
        <input type="hidden" name="action" value="add">
        <input type="submit" value="Add Book">
    </form>
</body>
</html>
