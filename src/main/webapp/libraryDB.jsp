<%@ page import="org.tgieralt.models.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library Database</title>
</head>
<body>
<%
    List<Book> bookList = (List<Book>) application.getAttribute("books");
%>
<div>
    <ol>
    <%
        for (Book book : bookList) {
            %><li><%=book.toString()%><%
        }
    %></ol>
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

</body>
</html>
