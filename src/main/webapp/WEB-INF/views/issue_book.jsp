<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE HTML>
<html>

<head>
  <title>Issue Book</title>
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
          <li class="selected"><a href="/issues/new">Issue Book</a></li>
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
        <h1>Issue Book</h1>

        <table style="width:100%; border-spacing:0;">
            <tr>
                <td>Member Id ${member.getLibraryId()}</td>
                <td>Total Issued: ${count} / ${member.getLimit()}</td>
             </tr>
             <tr>
                <td>Name: ${member.getName()}</td>
                <td>Category: ${member.getCategory()}</td>
             </tr>
            <tr>
                <td>Phone: ${member.getPhoneNumber()}</td>
                <td> <c:forEach var="email" items="${mails}">
                    <p style="font-size:12px;"><a href = "mailto:" + ${email} + "?subject = Library_Book(s)_Return">${email}</a></p>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>Address:  ${member.getCurrentLocation()},${member.getCurrentCity()},${member.getCurrentPincode()} </td>
                <td>Guarantor mail: <a href = "mailto:" + ${member.getGuarantorEmail()} + "?subject = Library_Book(s)_Return">${member.getGuarantorEmail()}</a></td>
            </tr>
         </table>

        <h1>Member Issue Details</h1>
        <table style="width:100%; border-spacing:0;">
            <tr>
                <th>BookId</th>
                <th>Issue Date</th>
                <th>Due On</th>
                <th>Return Date</th>
                <th>PhoneNumber</th>
                <th>Warning</th>
                <th>Remarks</th>
                <th>Fine</th>
                <th> </th>
            </tr>
            <c:forEach varStatus = "i" var="isu" items="${isuList}">
                <tr>
                    <td><a href="/books/${isu.getBookId()}">${isu.getBookId()}</a></td>
                    <td>${isu.getIssueDate()}</td>
                    <td>${isu.getDueDate()}</td>
                    <td>${isu.getReturnDate()}</td>
                    <td>${isu.getPhoneNumber()}</td>
                    <td>${isu.getWarning()}</td>
                    <td>${isu.getRemarks()}</td>
                    <td>${isu.getFine()}</td>
                    <td><a href="/issues/book/return/${isu.getIssueId()}">Edit</a></td>
                </tr>
            </c:forEach>
        </table>

          <form method="post" action="/issues/books/search/${member.getLibraryId()}" id="search_form">
            <p>
              <input class="search" type="text" name="BookId" onfocus="this.value=''" value="Enter Book Id....." />
              <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="/style/search.png" alt="Search" title="Search" />
            </p>
          </form>

      <c:if test="${show_book==1 || show_book==2}">
         <table style="width:100%; border-spacing:0;">
             <tr>
                 <td>Book Id ${book.getBookId()}</td>
                 <td>Address: Room${book.getRoomNumber()}:${book.getRackNumber()}-${book.getShelfNumber()}(${book.getSerialNumber()})</td>
              </tr>
              <tr>
                 <td>Title: ${book.getTitle()}</td>
                 <td>Writer: ${book.getWriter()}</td>
              </tr>
             <tr>
                 <td>Edition: ${book.getEdition()}</td>
                 <c:if test="${book.getBookCategory() == 'Available'}">
                    <td>Status: ${book.getBookStatus()}</td>
                 </c:if>
             </tr>
         </table>
      </c:if>

      <c:if test="${show_book==1}">
        <c:if test="${book.getBookCategory() == 'Available' && book.getBookStatus() == 'Available'}">
            <form:form action="/issues/${member.getLibraryId()}/book/add" method="POST" modelAttribute="issues">
              <div class="form_settings">
                <p><span>Book Id</span><form:input type="text" path="BookId" value="${issues.getBookId()}" /></p>
                <p><span>Issue Date</span><form:input type="Date" path="IssueDate" value="${issues.getIssueDate()}" /></p>
                <p><span>Due Date</span><form:input type="Date" path="DueDate" value="${issues.getDueDate()}" /></p>
                <p><span>Phone</span><form:input type="text" path="PhoneNumber" value="${issues.getPhoneNumber()}" /></p>

                <p style="padding-top: 15px"><span>&nbsp;</span><form:input class="submit" type="submit" value="Add Book" path="" /></p>
              </div>
            </form:form>
        </c:if>
      </c:if>

      <c:if test="${show_book==2}">
        <form:form action="/issues/book/return/${isu.getIssueId()}" method="POST" modelAttribute="isu">
          <div class="form_settings"
            <p><span>Book Id</span>${isu.getBookId()}</p>
            <p><span>Title</span>${book.getTitle()}</p>
            <p><span>Issue Date</span>${isu.getIssueDate()}</p>
            <p><span>Due Date</span>${isu.getDueDate()}</p>
            <p><span>Current delay</span>${isu.getCurrentDelay()}</p>
            <p><span>Return Date</span><form:input type="Date" path="ReturnDate" value="${isu.getReturnDate()}" /></p>
            <p><span>Warning</span><form:input type="text" path="Warning" value="${isu.getWarning()}" /></p>
            <p><span>Fine</span><form:input type="text" path="Fine" value="${isu.getFine()}" /></p>
            <p><span>Remarks</span><form:input type="text" path="Remarks" value="${isu.getRemarks()}" /></p>

            <p style="padding-top: 15px"><span>&nbsp;</span><form:input class="submit" type="submit" value="Return" path="" /></p>
          </div>
        </form:form>
      </c:if>

      </div>
    </div>
    <jsp:include page="footer.jsp"/>
  </div>
</body>
</html>