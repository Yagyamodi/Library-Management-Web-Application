<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE HTML>
<html>

<head>
  <title>All Issues</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  <link rel="stylesheet" type="text/css" href="/style/style.css" />
</head>

<body>
  <div id="main">
    <div id="header">
        <jsp:include page="logo.jsp"/>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
            <li class="selected"><a href="/">Home</a></li>
            <li><a href="/books">All Books</a></li>
            <li><a href="/issues/new">Issue Book</a></li>
            <li><a href="/members/new">Add Member</a></li>
          <c:choose>
            <c:when test="${pageContext.request.userPrincipal.name != null}">
              <li><a href="/logout">Log Out ( ${pageContext.request.userPrincipal.name} )</a></li>
            </c:when>
            <c:otherwise>
              <li><a href="/login">Log In</a></li>
            </c:otherwise>
          </c:choose>
        </ul>
      </div>
    </div>
    <div id="content_header"></div>
    <div id="site_content">
      <jsp:include page="sidebar.jsp"/>
      <div id="content">
        <!-- insert the page content here -->
        <h1>All Issues</h1>
        <table style="width:100%; border-spacing:0.1;">
            <tr>
                <th>Member</th>
                <th>Book</th>
                <th>Title</th>
                <th>Status</th>
                <th>Issued On</th>
                <th>Due On</th>
                <th>Phone</th>
                <th>Fine</th>
                <th>Returned On</th>
            </tr>
            <c:forEach varStatus = "i" var="isu" items="${issues}">
                <tr>
                    <td><a href="/issues/member/${isu.getLibraryId()}">${isu.getLibraryId()} </a></td>
                    <td><a href="/books/${isu.getBookId()}">${isu.getBookId()}</a></td>
                    <c:forEach var="book" items="${books}">
                        <c:choose>
                            <c:when test="${book.getBookId()== isu.getBookId()}">
                                <td>${book.getTitle()}</td>
                                <td>${book.getBookStatus()}</td>
                            </c:when>
                            <c:otherwise>
                              <% %>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <td>${isu.getIssueDate() }</td>
                    <td>${isu.getDueDate()}</td>
                    <td>${isu.getPhoneNumber()}</td>
                    <td>${isu.getFine()}</td>
                    <td>${isu.getReturnDate()}</td>
                </tr>
            </c:forEach>
        </table>
      </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>