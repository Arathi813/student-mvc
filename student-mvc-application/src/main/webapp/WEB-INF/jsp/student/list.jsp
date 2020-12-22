<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
    <head>
        <title>Student portal</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="/css/student.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <script  type="text/javascript" src="/js/student.js"></script>
        <script  type="text/javascript" src="/js/user.js"></script>

    </head>
    <body>
        <div class="container id="header"">
            <div class="text-center head-new">
                <h1> <span style="color:green"> Welcome to Student portal</span> </h1>
            </div>
            <div class="float-right">
                <button type="button" class="btn btn-success new-student" data-toggle="modal" data-target="#newModal">
                    <i class="fa fa-plus"></i> Student
                </button>
            </div>

            <div class="row messages">
                <c:if test="${message eq 'created'}">
                    <div class="alert alert-success alert-banner">
                        <strong> Successfully created!!!!!!  </strong>
                    </div>
                </c:if>

                <c:if test="${message eq 'updated'}">
                    <div class="alert alert-success alert-banner">
                        Successfully updated!!!!!!
                    </div>
                </c:if>

                <c:if test="${message eq 'deleted'}">
                    <div class="alert alert-danger alert-banner">
                        Successfully deleted!!!!!!
                    </div>
                </c:if>
            </div>
            <div class="row">
                <table class="table table-bordered">
                  <thead>
                    <tr>
                      <th scope="col">FirstName</th>
                      <th scope="col">LastName</th>
                      <th scope="col">Email</th>
                      <th scope="col"></th>
                      <th scope="col"></th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${studentList}" var="student">
                        <tr>
                            <td><c:out value="${student.firstName}"/></td>
                            <td><c:out value="${student.lastName}"/></td>
                            <td><c:out value="${student.email}"/></td>
                            <td>
                                <button type="button" class="btn btn-success" id="editStudent" value="${student.id}">Edit</button>
                            </td>
                            <td>
                                <a href="/student/delete/${student.id}" class="btn btn-success" role="button">
                                    <i class="fa fa-trash"></i> Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                  </tbody>
                </table>
            </div>
        </div>

        <div class="modal fade" id="newModal">
            <div class="modal-dialog">
              <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                  <h4 class="modal-title" id="modalName">Add new Student</h4>
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                  <form id="newForm">
                      <c:if test="${errorCode eq 'inValidData'}">
                          <div class="alert alert-danger" role="alert">
                             Invalid form details
                          </div>
                      </c:if>

                      <div class="notify" id="notification"></div>
                      <input type="hidden" name="id" id="studentId">
                      <div class="form-group">
                          <label for="firstName">Name:</label>
                          <input type="text" class="form-control" id="firstName" placeholder="Enter Name" name="firstName" value="${student.firstName}" >
                      </div>

                      <div class="form-group">
                          <label for="email">Email:</label>
                          <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${student.email}" >
                      </div>
                  </form>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                   <button type="button" class="btn btn-success hidden" id="createStudentSubmit">Create</button>
                   <button type="button" class="btn btn-success hidden" id="editStudentSubmit">Save</button>
                   <button type="button" class="btn btn-success" data-dismiss="modal">Close</button>
                </div>

              </div>
            </div>
        </div>

    </body>
</html>

