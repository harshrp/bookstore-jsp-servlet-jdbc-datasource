# Bookstore Web Application

This is a simple web application for managing a bookstore. It demonstrates the use of JSP, Servlet, and JDBC with Datasource for CRUD operations with a MySQL database.

## Features

- Display a list of books from the database.
- Add new books to the database.
- Update existing books.
- Delete books from the database.

## Technologies Used

- Java Servlets
- JavaServer Pages (JSP)
- JDBC (Java Database Connectivity)
- DBCP2 BasicDataSource
- MySQL
- Apache Tomcat (Servlet container)
- Maven (Build tool)

## Project Structure

- **src/main/java/org/example**: Contains Java source files.
    - `Book.java`: Entity class representing a book.
    - `BookServlet.java`: Servlet for handling HTTP requests.
	- `MyDataSourceFactory `: Factory class for creating DataSource.
- **src/main/webapp**: Contains JSP files.
    - `index.jsp`: JSP page to display the list of books and manage CRUD operations.
- **src/main/webapp/css**: Contains CSS file.
  - `style.css` : Simple CSS Stylesheet

## Database Configuration

- Create a MySQL database named `booksdb`.
- Execute the provided SQL script (`booksdb.sql`) to create the `books` table and insert sample data.

### Database Creation Script

```sql
-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS booksdb;

-- Switch to the booksdb database
USE booksdb;

-- Create the books table
CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL
);

-- Insert sample data into the books table
INSERT INTO books (title, author) VALUES
('To Kill a Mockingbird', 'Harper Lee'),
('1984', 'George Orwell'),
('The Great Gatsby', 'F. Scott Fitzgerald'),
('Pride and Prejudice', 'Jane Austen'),
('The Catcher in the Rye', 'J.D. Salinger');
```
## Deployment
- Build the project using Maven: `mvn clean install`.
- Deploy the generated WAR file (bookstore-jsp-servlet-jdbc-1.0.war) to a Servlet container like Apache Tomcat.

## Usage
- Access the application at `http://localhost:8080/bookstore-jsp-servlet-jdbc-datasource-1.0/books`.
- You'll see a list of books from the database.
- You can add, update, or delete books using the provided UI.

## License
This project is licensed under the MIT License