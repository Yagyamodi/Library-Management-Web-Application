<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE HTML>
<html>

<head>
  <title>New Supplier</title>
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
        <h1>Supplier details: </h1>
            <form:form action="/suppliers/new" method="POST" modelAttribute="supplier">
              <div class="form_settings">
                <p><span>Supplier Name</span><form:input type="text" path="SupplierName" value="${supplier.getSupplierName()}" /></p>
                <p><span>Shop Name</span><form:input type="text" path="ShopName" value="${supplier.getShopName()}" /></p>
                <p><span>Address</span><form:input type="text" path="ShopLocation" value="${supplier.getShopLocation()}" /></p>
                <p><span>City</span><form:input type="text" path="ShopCity" value="${supplier.getShopCity()}" /></p>
                <p><span>Pincode</span><form:input type="text" path="ShopPincode" value="${supplier.getShopPincode()}" /></p>
                <p><span>State</span><form:input type="tel" path="ShopState" value="${supplier.getShopState()}" /></p>
                <p><span>Catalog</span><form:input type="text" path="Catalog" value="${supplier.getCatalog()}" /></p>
                <p><span>Phone</span><form:input type="text" path="PhoneNumber" value="${supplier.getPhoneNumber()}" /></p>
                <p><span>Email</span><form:input type="text" path="EmailAddress" value="${supplier.getEmailAddress()}" /></p>

                <p style="padding-top: 15px"><span>&nbsp;</span><form:input class="submit" type="submit" value="Add" path="" /></p>
              </div>
            </form:form>

      </div>
    </div>
    <jsp:include page="footer.jsp"/>
  </div>
</body>
</html>