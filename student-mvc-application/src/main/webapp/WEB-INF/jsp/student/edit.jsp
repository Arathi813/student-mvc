<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<head>
    <title>Student form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <h2>Student Details form</h2>
    <form action="/student/update" method="post">
        <c:if test="${errorCode eq 'inValidData'}">
            <div class="alert alert-danger" role="alert">
                The form data is invalid!!!
            </div>
        </c:if>
        <input type="text" name="id" hidden value="${student.id}">

        <div class="form-group">
            <label for="firstName">Name:</label>
            <input type="text" class="form-control" id="firstName" placeholder="Enter Name" name="firstName" value="${student.firstName}">
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${student.email}">
        </div>

        <button type="submit" class="btn btn-default">Submit</button>
    </form>
</div>

</body>
</html>
