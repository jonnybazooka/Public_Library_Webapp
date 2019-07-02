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

<div class="home">
    <a href="index.jsp">Home</a>
</div>

</body>
</html>
