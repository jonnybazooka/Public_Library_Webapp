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

</body>
</html>
