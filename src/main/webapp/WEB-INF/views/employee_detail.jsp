<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  


<!DOCTYPE HTML>
<html>

<head>
  <title>Employee Detail</title>
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
        <h1>Employee Details</h1>
        <form:form action="/employees/${employee.employeeId}/edit" method="POST" modelAttribute="employee">
          <div class="form_settings">
            <p><span>First Name</span><form:input type="text" path="Fname" value="${employee.getFname()}" /></p>
            <p><span>Last Name</span><form:input type="text" path="Lname" value="${employee.getLname()}" /></p>
            <p><span>Address</span><form:input type="text" path="address" value="${employee.getAddress()}" /></p>
            <p><span>Contact No.</span><form:input type="tel" path="contact" value="${employee.getContact()}" /></p>
            <p><span>Gender</span>
                <form:select id="id" path="gender">
                  <c:choose>
                    <c:when test="${employee.getGender()=='M'}">
                      <option value="M" selected>Male</option>
                      <option value="F" >Female</option>
                    </c:when>
                    <c:otherwise>
                      <option value="M" >Male</option>
                      <option value="F" selected>Female</option>
                    </c:otherwise>
                  </c:choose>
                </form:select>
            </p>
            <p><span>Date of Birth</span><form:input type="date" path="DOB" value="${employee.getDOB()}" /></p>
            <p><span>Designation</span><form:select path="designation">
                <c:choose >
                    <c:when test="${employee.getDesignation()=='Teacher'}">
                        <option value="Teacher" selected>Teacher</option>
                    </c:when>
                    <c:otherwise>
                        <option value="Teacher">Teacher</option>
                    </c:otherwise>
                </c:choose>
                <c:choose >
                    <c:when test="${employee.getDesignation()=='Manager'}">
                        <option value="Manager" selected>Manager</option>
                    </c:when>
                    <c:otherwise>
                        <option value="Manager">Manager</option>
                    </c:otherwise>
                </c:choose>
                <c:choose >
                    <c:when test="${employee.getDesignation()=='None'}">
                        <option value="None" selected>None</option>
                    </c:when>
                    <c:otherwise>
                        <option value="None">None</option>
                    </c:otherwise>
                </c:choose>
            </form:select></p>
            <p><span>e-mail</span><form:input type="email" path="email" value="${employee.getEmail()}" /></p>
            <p style="padding-top: 15px"><span>&nbsp;</span><form:input class="submit" type="submit" value="update" path="" /></p>
          </div>
        </form:form>
        
        <h2>Credentials</h2>
        <c:set var = "f" value = "${0}"/>
          <c:forEach var="info" items="${userinfo}">
              <c:choose>
                  <c:when test="${employee.getEmployeeId()== info.getEmployeeId()}">
                          <c:set var = "f" value = "${1}"/>
                      <form action="/credentials/${employee.getEmployeeId()}/edit" method="POST">
                          <div class="form_settings">
                              <p><span>username</span>${info.getUserName()}</p>
                              <p><span>Role</span>
                                <select id="id" path="" name="role">
                                  <c:choose>
                                    <c:when test="${info.getRole()=='ROLE_ADMIN'}">
                                      <option value="ROLE_ADMIN" selected>ROLE_ADMIN</option>
                                      <option value="ROLE_EMPLOYEE" >ROLE_EMPLOYEE</option>
                                      <option value="NONE" >NONE</option>
                                    </c:when>
                                    <c:when test="${info.getRole()=='ROLE_EMPLOYEE'}">
                                      <option value="ROLE_ADMIN" >ROLE_ADMIN</option>
                                      <option value="ROLE_EMPLOYEE" selected>ROLE_EMPLOYEE</option>
                                      <option value="NONE" >NONE</option>
                                    </c:when>
                                    <c:otherwise>
                                      <option value="ROLE_ADMIN" >ROLE_ADMIN</option>
                                      <option value="ROLE_EMPLOYEE" >ROLE_EMPLOYEE</option>
                                      <option value="NONE" selected>NONE</option>
                                    </c:otherwise>
                                  </c:choose>
                                </select>
                              </p>
                              <p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" value="update" path="" /></p>
                          </div>
                      </form>                        
                  </c:when>
              </c:choose>
          </c:forEach>   
          <c:choose>
              <c:when test="${ f==0 }">
                <div class="form_settings">
                    <p style="padding-top: 15px"><span></span><a href="/credentials/${employee.getEmployeeId()}/new"><button class ="submit" >Assign Credentails</button></a></p>
                </div>
              </c:when>
          </c:choose>         
          <c:set var = "f" value = "${0}"/>

      </div>
    </div>
    <jsp:include page="footer.jsp"/>
  </div>
</body>
</html>