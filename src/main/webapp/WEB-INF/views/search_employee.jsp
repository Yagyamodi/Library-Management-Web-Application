<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<html>
    <head>
        <title>
            Search Employee
        </title>
    </head>
    <body>
        <h1>Search Employee</h1>
        <form method="POST" action="/employees/search">
        <table style="width: 50%">
            <tr>
                <td>Employee Id</td>
                <td><input type="Number" name="employeeId" /></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="search" />
        </form>
    </body>
</html>