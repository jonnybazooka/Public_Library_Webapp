<%@ page import="org.tgieralt.models.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library</title>
</head>
<body>
<div class="list-view">
    <%
        List<Book> myLibrary = (List<Book>) application.getAttribute("myLibrary");
        for (Book book : myLibrary) {
        %><li><%=book.toString()%></><%
        }
    %>
</div>

<div>
    <form method="POST" action="libraryDB">
        <p>Title</p>
        <input type="text" name="title">
        <p>Author</p>
        <input type="text" name="author">
        <p>ISBN Number</p>
        <input type="text" name="isbn">
        <p>Add new book to the library</p>
        <input type="submit" value="Add Book">
    </form>
</div>

<div class="home">
    <a href="libraryDB">Library Database</a>
    <a href="index.jsp">Home</a>
</div>

</body>
</html>
