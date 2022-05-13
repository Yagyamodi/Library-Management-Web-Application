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
        <h1>Book details: </h1>
            <form:form action="/books/new" method="POST" modelAttribute="book">
              <div class="form_settings">
                <p><span>Id</span><form:input type="text" path="BookId" value="${book.getBookId()}" /></p>
                <p><span>Title</span><form:input type="text" path="Title" value="${book.getTitle()}" /></p>
                <p><span>Writer</span><form:input type="text" path="Writer" value="${book.getWriter()}" /></p>
                <p><span>Publisher</span><form:input type="text" path="Publisher" value="${book.getPublisher()}" /></p>
                <p><span>Publish Year</span><form:input type="text" path="PublishYear" value="${book.getPublishYear()}" /></p>
                <p><span>Edition</span><form:input type="tel" path="Edition" value="${book.getEdition()}" /></p>
                <p><span>Volume</span><form:input type="text" path="Volume" value="${book.getVolume()}" /></p>
                <p><span>Pages</span><form:input type="text" path="Pages" value="${book.getPages()}" /></p>

                <p><span>SubjectName</span><form:select path="SubjectNumber">
                    <c:forEach varStatus = "i" var="sub" items="${subjects}">
                         <c:choose >
                           <c:when test="${book.getSubjectNumber()==sub.getSubjectNumber()}">
                               <option value="${sub.getSubjectNumber()}" selected>${sub.getSubjectName()}</option>
                           </c:when>
                           <c:otherwise>
                               <option value="${sub.getSubjectNumber()}">${sub.getSubjectName()}</option>
                           </c:otherwise>
                         </c:choose>
                     </c:forEach>
                </form:select></p>

                <p><span>Language</span><form:select path="Language">
                    <c:choose>
                      <c:when test="${book.getLanguage()=='English'}">
                        <option value="English" selected>English</option>
                      </c:when>
                      <c:otherwise>
                        <option value="English">English</option>
                      </c:otherwise>
                    </c:choose>
                    <c:choose >
                      <c:when test="${book.getLanguage()=='Hindi'}">
                          <option value="Hindi" selected>Hindi</option>
                      </c:when>
                      <c:otherwise>
                          <option value="Hindi">Hindi</option>
                      </c:otherwise>
                    </c:choose>
                     <c:choose >
                       <c:when test="${book.getLanguage()=='Sanskrit'}">
                           <option value="Sanskrit" selected>Sanskrit</option>
                       </c:when>
                       <c:otherwise>
                           <option value="Sanskrit">Sanskrit</option>
                       </c:otherwise>
                     </c:choose>
                </form:select></p>

                <p><span>BookCategory</span><form:select path="BookCategory">
                    <c:choose>
                      <c:when test="${book.getBookCategory()=='Available'}">
                        <option value="Available" selected>Available</option>
                      </c:when>
                      <c:otherwise>
                        <option value="Available">Available</option>
                      </c:otherwise>
                    </c:choose>
                    <c:choose >
                      <c:when test="${book.getBookCategory()=='Damaged'}">
                          <option value="Damaged" selected>Damaged</option>
                      </c:when>
                      <c:otherwise>
                          <option value="Damaged">Damaged</option>
                      </c:otherwise>
                    </c:choose>
                     <c:choose >
                       <c:when test="${book.getBookCategory()=='Not to be Issued'}">
                           <option value="Not to be Issued" selected>Not to be Issued</option>
                       </c:when>
                       <c:otherwise>
                           <option value="Not to be Issued">Not to be Issued</option>
                       </c:otherwise>
                     </c:choose>
                    <c:choose >
                      <c:when test="${book.getBookCategory()=='Out of Stock'}">
                          <option value="Out of Stock" selected>Out of Stock</option>
                      </c:when>
                      <c:otherwise>
                          <option value="Out of Stock">Out of Stock</option>
                      </c:otherwise>
                    </c:choose>
                </form:select></p>

                <p><span>BookStatus</span><form:select path="BookStatus">
                    <c:choose>
                      <c:when test="${book.getBookStatus()=='Available'}">
                        <option value="Available" selected>Available</option>
                      </c:when>
                      <c:otherwise>
                        <option value="Available">Available</option>
                      </c:otherwise>
                    </c:choose>
                    <c:choose >
                      <c:when test="${book.getBookStatus()=='Issued'}">
                          <option value="Issued" selected>Issued</option>
                      </c:when>
                      <c:otherwise>
                          <option value="Issued">Issued</option>
                      </c:otherwise>
                    </c:choose>
                     <c:choose >
                       <c:when test="${book.getBookStatus()=='Pending'}">
                           <option value="Pending" selected>Pending</option>
                       </c:when>
                       <c:otherwise>
                           <option value="Pending">Pending</option>
                       </c:otherwise>
                     </c:choose>
                </form:select></p>

                 <p><span>Room Number</span><form:input type="text" path="RoomNumber" value="${book.getRoomNumber()}" /></p>
                 <p><span>Rack Number</span><form:input type="text" path="RackNumber" value="${book.getRackNumber()}" /></p>
                 <p><span>Shelf Number</span><form:input type="text" path="ShelfNumber" value="${book.getShelfNumber()}" /></p>
                 <p><span>Serial Number</span><form:input type="text" path="SerialNumber" value="${book.getSerialNumber()}" /></p>
                 <p><span>Frequency</span><form:input type="text" path="Frequency" value="${book.getFrequency()}" /></p>

                <p style="padding-top: 15px"><span>&nbsp;</span><form:input class="submit" type="submit" value="Add" path="" /></p>
              </div>
            </form:form>

      </div>
    </div>
    <jsp:include page="footer.jsp"/>
  </div>
</body>
</html>