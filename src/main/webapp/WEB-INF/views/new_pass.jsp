<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  


<!DOCTYPE HTML>
<html>

<head>
  <title>Register User</title>
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
          <h1>Change Password</h1>
          <p>${msg}</p>
          <form action="/credentials/${pageContext.request.userPrincipal.name}/change" method="POST">
              <div class="form_settings">
                  <p><span>Old Password</span><input type="text" name="oldPassword" path="" value="" /></p>
                  <p><span>New Password</span><input type="text" name="newPassword" path="" value="" /></p>
                  <p><span>Confirm Password</span><input type="text" name="confirmPassword" path="" value="" /></p>
                  <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" value="Submit" path="" /></p>
              </div>
          </form>                        
      </div>
    </div>
    <jsp:include page="footer.jsp"/>
  </div>
</body>
</html>