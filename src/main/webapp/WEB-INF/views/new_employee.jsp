<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  


<!DOCTYPE HTML>
<html>

<head>
  <title>Add Employee</title>
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
        <h1>Add Employee</h1>
        <form:form action="/employees/new" method="POST" modelAttribute="employee">
          <div class="form_settings">
            <p><span>First Name</span><form:input type="text" path="Fname" value="" /></p>
            <p><span>Last Name</span><form:input type="text" path="Lname" value="" /></p>
            <p><span>Address</span><form:input type="text" path="address" value="" /></p>
            <p><span>Contact No.</span><form:input type="tel" path="contact" value="" /></p>
            <p><span>Gender</span>
                <form:select id="id" path="gender">
                    <option value="M" selected>Male</option>
                    <option value="F" >Female</option>
                </form:select>
            </p>
            <p><span>Date of Birth</span><form:input type="date" path="DOB" value="" /></p>
            <p><span>Designation</span><form:select path="designation">
                <option value="Co-Employee" selected>Co-Employee</option>
                <option value="Manager">Manager</option>
                <option value="None">None</option>
            </form:select></p>
            <p><span>e-mail</span><form:input type="email" path="email" value="" /></p>
            <p style="padding-top: 15px"><span>&nbsp;</span><form:input class="submit" type="submit" value="submit" path="" /></p>
          </div>
        </form:form>
      </div>
    </div>
    <jsp:include page="footer.jsp"/>
  </div>
</body>
</html>