<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>

<head>
  <title>Home Page</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  <link rel="stylesheet" type="text/css" href="style/style.css" />
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
      <div id="banner"></div>
      <jsp:include page="sidebar.jsp"/>
      <div id="content">
        <!-- insert the page content here -->
        <h1>Rajasthan.gov.in</h1>
        <p>Jhunjhunu Library - Modi Road, Jhunjhunun (Rajasthan)</p>
        <h3>The Vision to Study</h3>
        <!-- <p>Jai Gurudev :)</p> -->
        <p>This is a web-based portal of Government e-Library made in attempt to smoothen the organization functioning. Readers can see all the books available in library online. This is for the kind information that library now use this digital platform for all functioning.</p>
      </div>
    </div>
    <jsp:include page="footer.jsp"/>
  </div>
</body>
</html>