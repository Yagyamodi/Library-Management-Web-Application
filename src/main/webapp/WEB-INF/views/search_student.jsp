<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  

<html>
    <head>
        <title>
            Search Student
        </title>
    </head>
    <body>
        <h1>Search Student</h1>
        <form method="POST" action="/students/search">
        <table style="width: 50%">
            <tr>
                <td>Serial Number</td>
                <td><input type="Number" name="serialNumber" /></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="search" />
        </form>
    </body>
</html>