<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE HTML>
<html>

<head>
  <title>New Withdrawal</title>
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
        <h1>Withdrawal details: </h1>
        <form:form action="/withdrawals/new" method="POST" modelAttribute="withdrawal">
          <div class="form_settings">
            <p><span>Book</span><form:select path="BookId">
                <c:forEach varStatus = "i" var="book" items="${books}">
                     <c:choose >
                       <c:when test="${withdrawal.getBookId()==book.getBookId()}">
                           <option value="${book.getBookId()}" selected>${book.getBookId()} ${book.getTitle()}</option>
                       </c:when>
                       <c:otherwise>
                           <option value="${book.getBookId()}">${book.getBookId()} ${book.getTitle()}</option>
                       </c:otherwise>
                     </c:choose>
                 </c:forEach>
            </form:select></p>
            <p><span>Reason</span><form:input type="text" path="Reason" value="${withdrawal.getReason()}" /></p>
            <p><span>Date</span><form:input type="Date" path="WithdrawalDate" value="${withdrawal.getWithdrawalDate()}" /></p>

            <p style="padding-top: 15px"><span>&nbsp;</span><form:input class="submit" type="submit" value="Add" path="" /></p>
          </div>
        </form:form>

      </div>
    </div>
    <jsp:include page="footer.jsp"/>
  </div>
</body>
</html>