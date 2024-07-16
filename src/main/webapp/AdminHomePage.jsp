 <%@ page isELIgnored="false" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AdminHome</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <!--  <base href="http://localhost:8080/FinalProject/" />  -->
    <style>
        body {
            background-color: #f0f2f5;
            margin: 0;
        }
        .signup-form {
                    width: 360px;
                    margin: 100px auto;
                    font-family: Arial, sans-serif;
                }
                .signup-form form {
                    background: #fff;
                    padding: 30px;
                    border-radius: 10px;
                    box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
                }
        .navbar {
            margin-bottom: 20px;
        }
        .logo-img {
            margin-right: 15px;
        }
        .form-container {
            padding-top: 80px;
        }
         .navbar-nav {
            align-items: center;
            display: flex;
            justify-content: center;
            height: 100%;
        }
        h2{
        position:relative;
        left:500px;
        top:150px;
        margin-bottom:30px;
        width:700px;
        }
        .tableOut{
        position:relative;
        top:150px;
          margin-left: 60px;
          margin-right:100px;
          width: 90%;
        }

    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item" id="img">
                    <img src="/FinalProject/images/xworkz.jpg" width="140" height="70" alt="Xworkz" class="logo-img">
                </li>

                <li class="nav-item" id="SignIn.jsp">
                       <a class="nav-link text-light" href="index.jsp">Home</a>
                </li>
                <li>
                <a class="nav-link active text-light fs-5 fw-bold" aria-current="page" href="viewDetails">userDetails</a>
                </li>
                <li>
                <a class="nav-link active text-light fs-5 fw-bold" aria-current="page" href="viewComplaintDetails">ComplaintDetailsDetails</a>
                </li>
            </ul>
        </div>
    </nav>
    <c:if test="${dto.isEmpty()==false}">
<h2> View All User Details.. </h2>
<div class="tableOut">
        <span style="color:red">${msg} </span>
            <table class="table">
              <thead class="thead-light">
                <tr>
                 <th scope="col">Id</th>
                  <th scope="col">First Name</th>
                  <th scope="col">Last Name</th>
                  <th scope="col">Email</th>
                  <th scope="col">Phone number</th>
                  <th scope="col">Login count</th>
                  <th scope="col">Created By</th>
                  <th scope="col">Created Date</th>
                  <th scope="col">Updated By</th>
                  <th scope="col">Updated Date</th>

              </tr>
              </thead>
              <tbody>
              <c:forEach items="${dto}" var="signup">
                <tr>
                  <td scope="row">${signup.getId()}</td>
                  <td>${signup.getFirstName()}</td>
                  <td>${signup.getLastName()}</td>
                  <td>${signup.getEmail()}</td>
                  <td>${signup.getPhoneNumber()}</td>
                  <td>${signup.getLogin_count()}</td>
                  <td>${signup.getCreatedBy()}</td>
                  <td>${signup.getCreatedDate()}</td>
                  <td>${signup.getUpdatedBy()}</td>
                  <td>${signup.getUpdatedDate()}</td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
</div>
</c:if>
</body>
</html>
