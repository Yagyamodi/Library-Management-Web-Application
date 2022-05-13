<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE HTML>
<html>

<head>
  <title>Supplier</title>
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
        <h1>Supplier Detail</h1>
        <table style="width:100%; border-spacing:0;">
                <tr>
                    <td>Supplier Id ${supplier.getSupplierId()}</td>
                    <td>Name: ${supplier.getSupplierName()}</td>
                </tr>
                <tr>
                    <td>Shop: ${supplier.getShopName()}</td>
                    <td>Phone: ${supplier.getPhoneNumber()}</td>
                </tr>
                <tr>
                    <td>Address: ${supplier.getShopLocation()},${supplier.getShopCity()}</td>
                    <td>Pincode: ${supplier.getShopPincode()}</td>
                </tr>
        </table>

        <h1>Purchases</h1>
        <table style="width:100%; border-spacing:0;">
            <tr>
                <th>BookId</th>
                <th>BillNumber</th>
                <th>Cost</th>
                <th>Purchase Year</th>
            </tr>
            <c:forEach varStatus = "i" var="pur" items="${all_purchases}">
                <tr>
                    <td><a href= "/books/${pur.getBookId()}">${pur.getBookId()}</a></td>
                    <td>${pur.getBillNumber()}</td>
                    <td>${pur.getCost()}.0/-</td>
                    <td>${pur.getPurchaseYear()}</td>
                </tr>
            </c:forEach>
        </table>
      </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>