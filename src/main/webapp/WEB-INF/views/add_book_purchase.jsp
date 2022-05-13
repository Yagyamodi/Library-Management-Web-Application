<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE HTML>
<html>

<head>
  <title>New Book</title>
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

        <table style="width:100%; border-spacing:0;">
          <tr>
            <td>Book Id ${book.getBookId()}</td>
            <td>Frequency: ${book.getFrequency()}</td>
          </tr>
          <tr>
            <td>Title: ${book.getTitle()}</td>
            <td>Writer: ${book.getWriter()}</td>
          </tr>
          <tr>
            <td>Edition: ${book.getEdition()}</td>
            <td>Category: ${book.getBookCategory()}</td>
          </tr>
          <tr>
            <td>Subject: ${subject_name}</td>
            <td>Language: ${book.getLanguage()}</td>
          <tr>
            <td>Address: Room ${book.getRoomNumber()}: Rack ${book.getRackNumber()} - ${book.getShelfNumber()}(${book.getSerialNumber()})</td>
            <td><span td:if="${book.getBookCategory() == 'Available'}">Status: ${book.getBookStatus()}</span></td>
          </tr>
        </table>

        <h1>Add Purchase Details:</h1>
        <form:form action="/books/purchase/new" method="POST" modelAttribute="purchase">
          <div class="form_settings">
            <p><span>BookId</span><form:input type="text" path="BookId" value="${purchase.getBookId()}" /></p>
            <p><span>Bill Number</span><form:input type="text" path="BillNumber" value="${purchase.getBillNumber()}" /></p>
            <p><span>Cost</span><form:input type="text" path="Cost" value="${purchase.getCost()}" /></p>
            <p><span>Purchase Year</span><form:input type="text" path="PurchaseYear" value="${purchase.getPurchaseYear()}" /></p>
            <p><span>Supplier</span><form:select path="SupplierId">
                <c:forEach varStatus = "i" var="sup" items="${suppliers}">
                     <c:choose >
                       <c:when test="${purchase.getSupplierId()==sup.getSupplierId()}">
                           <option value="${sup.getSupplierId()}" selected>${sup.getSupplierName()}</option>
                       </c:when>
                       <c:otherwise>
                           <option value="${sup.getSupplierId()}">${sup.getSupplierName()}</option>
                       </c:otherwise>
                     </c:choose>
                 </c:forEach>
            </form:select></p>

            <p style="padding-top: 15px"><span>&nbsp;</span><form:input class="submit" type="submit" value="Add" path="" /></p>
          </div>
        </form:form>

      </div>
    </div>
    <jsp:include page="footer.jsp"/>
  </div>
</body>
</html>