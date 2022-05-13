<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE HTML>
<html>

<head>
  <title>New Member</title>
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
          <li><a href="/">Home</a></li>
          <li><a href="/books">All Books</a></li>
          <li><a href="/issues/new">Issue Book</a></li>
          <li class="selected"><a href="/members/new">Add Member</a></li>
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

        <h1>Member details: </h1>
            <form:form action="/members/new" method="POST" modelAttribute="member">
              <div class="form_settings">
                <p><span>Name</span><form:input type="text" path="Name" value="${member.getName()}" /></p>

                <p><span>Gender</span>
                    <form:select id="id" path="gender">
                      <c:choose>
                        <c:when test="${member.getGender()=='M'}">
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

                <p><span>Category</span><form:select path="Category">
                    <c:choose>
                        <c:when test="${member.getCategory()=='Male'}">
                            <option value="Male" selected>Male</option>
                        </c:when>
                        <c:otherwise>
                            <option value="Male">Male</option>
                        </c:otherwise>
                    </c:choose>
                    <c:choose >
                        <c:when test="${member.getCategory()=='Female'}">
                            <option value="Female" selected>Female</option>
                        </c:when>
                        <c:otherwise>
                            <option value="Female">Female</option>
                        </c:otherwise>
                    </c:choose>
                    <c:choose >
                        <c:when test="${member.getCategory()=='Senior Citizen'}">
                            <option value="Senior Citizen" selected>Senior Citizen</option>
                        </c:when>
                        <c:otherwise>
                            <option value="Senior Citizen">Senior Citizen</option>
                        </c:otherwise>
                    </c:choose>
                    <c:choose >
                        <c:when test="${member.getCategory()=='Children'}">
                            <option value="Children" selected>Children</option>
                        </c:when>
                        <c:otherwise>
                            <option value="Children">Children</option>
                        </c:otherwise>
                    </c:choose>
                </form:select></p>

                <p><span>Phone Number</span><form:input type="tel" path="PhoneNumber" value="${member.getPhoneNumber()}" /></p>
                <p><span>Adhaar Number</span><form:input type="text" path="AdhaarNumber" value="${member.getAdhaarNumber()}" /></p>
                <p><span>DOB</span><form:input type="Date" path="DOB" value="${member.getDOB()}" /></p>
                <p><span>Education</span><form:input type="text" path="Education" value="${member.getEducation()}" /></p>
                <p><span>Profession</span><form:input type="text" path="Profession" value="${member.getProfession()}" /></p>
                <p><span>Age</span><form:input type="text" path="Age" value="${member.getAge()}" /></p>
                <p><span>Limit</span><form:input type="text" path="Limit" value="${member.getLimit()}" /></p>
                <p><span>Father Name</span><form:input type="text" path="FatherName" value="${member.getFatherName()}" /></p>
                <p><span>Join Date</span><form:input type="Date" path="JoinDate" value="${member.getJoinDate()}" /></p>
                <p>Address:</p>
                <p><span>Location</span><form:input type="text" path="CurrentLocation" value="${member.getCurrentLocation()}" /></p>
                <p><span>City</span><form:input type="text" path="CurrentCity" value="${member.getCurrentCity()}" /></p>
                <p><span>Pincode</span><form:input type="text" path="CurrentPincode" value="${member.getCurrentPincode()}" /></p>
                <p><span>State</span><form:input type="text" path="CurrentState" value="${member.getCurrentState()}" /></p>
                <p>Guarantor Details:</p>
                <p><span>Name</span><form:input type="text" path="GuarantorName" value="${member.getGuarantorName()}" /></p>
                <p><span>Government Department</span><form:input type="text" path="GuarantorDepartment" value="${member.getGuarantorDepartment()}" /></p>
                <p><span>Guarantor Email</span><form:input type="text" path="GuarantorEmail" value="${member.getGuarantorEmail()}" /></p>
                <p><span>Guarantor Mobile</span><form:input type="text" path="GuarantorPhone" value="${member.getGuarantorPhone()}" /></p>
                <p><span>Relation</span><form:input type="text" path="Relation" value="${member.getRelation()}" /></p>

                <p style="padding-top: 15px"><span>&nbsp;</span><form:input class="submit" type="submit" value="Update" path="" /></p>
              </div>
            </form:form>
      </div>
    </div>
    <jsp:include page="footer.jsp"/>
  </div>
</body>
</html>