<%@ page import="org.tgieralt.models.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Public Library</title>
    <script src="jquery-3.4.1.js"></script>
    <script>
        $(function () {
            $("#nav").load('nav.html');
        });
    </script>
</head>
<body>
<div id="nav"></div>
<div class="container">
    <div class="row">
        <h1>Welcome to my public library!</h1>
        <p><strong>You can find any valuable book here. Just register and start reading.</strong> Our finest book database will respond quickly to your request, and the bookkeeper will work tirelessly to find new books to our library.</p>
    </div>
</div>

<div class="container">
    <div class="row">
        <img src="graphics/books-header.jpg" class="img-fluid">
    </div>
</div>
<div class="container">
    <%
        List<Book> myLibrary = (List<Book>) application.getAttribute("myLibrary");
        int count = 1;
    %>
    <div class="row">
        <table class="table table-dark">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Author</th>
                <th scope="col">ISBN</th>
            </tr>
            </thead>
            <tbody>
            <% for (Book book : myLibrary) {
            %><tr>
                <th><%=count++%></th>
                <th><%=book.getTitle()%></th>
                <th><%=book.getAuthor()%></th>
                <th><%=book.getIsbn()%></th>
            </tr>
            <%  }  %>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
