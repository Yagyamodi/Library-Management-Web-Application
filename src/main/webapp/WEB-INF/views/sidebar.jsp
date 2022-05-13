<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  


<div id="sidebar_container">
  <!-- <div class="sidebar">
    <div class="sidebar_top"></div>
    <div class="sidebar_item">
      <h3>Latest News</h3>
      <h4>New Website Launched</h4>
      <h5>August 1st, 2020</h5>
      <p>2020 sees the redesign of our website. Take a look around and let us know what you think.<br /><a href="#">Read more</a></p>
    </div>
    <div class="sidebar_base"></div>
  </div> -->
  <div class="sidebar">
    <div class="sidebar_top"></div>
    <div class="sidebar_item">
      <h3>Useful Links</h3>
      <ul>
        <li><a href="/members">Present Members</a></li>
        <li><a href="/issues">All Books Issues</a></li>
        <li><a href="/subjects/all">All Subjects</a></li>
        <li><a href="/purchases">All Purchases</a></li>
        <li><a href="/suppliers">All Suppliers</a></li>
        <li><a href="/withdrawals">All Withdrawals</a></li>
        <li><a href="/books/new">Add Book</a></li>
        <li><a href="/employees">Employees</a></li>
        <li><a href="/credentials/change">Change Password</a></li>
      </ul>
    </div>
    <div class="sidebar_base"></div>
  </div>
  <div class="sidebar">
    <div class="sidebar_top"></div>
    <div class="sidebar_item">
      <h3>Member Search</h3>
      <form method="post" action="/members/search" id="search_form">
        <p>
          <input class="search" type="text" name="LibraryId" onfocus="this.value=''" value="Enter Library Id....." />
          <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="/style/search.png" alt="Search" title="Search" />
        </p>
      </form>
    </div>
    <div class="sidebar_base"></div>
  </div>
  <div class="sidebar">
    <div class="sidebar_top"></div>
    <div class="sidebar_item">
      <h3>Book Search</h3>
      <form method="post" action="/books/search" id="search_form">
        <p>
          <input class="search" type="text" name="BookId" onfocus="this.value=''" value="Enter Book Number....." />
          <input name="search" type="image" style="border: 0; margin: 0 0 -9px 5px;" src="/style/search.png" alt="Search" title="Search" />
        </p>
      </form>
    </div>
    <div class="sidebar_base"></div>
  </div>
</div>