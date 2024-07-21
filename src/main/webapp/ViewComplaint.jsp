 <%@ page isELIgnored="false" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>viewComplaint</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <base href="http://localhost:8080/FinalProject/" />
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
        left:400px;
        top:150px;
        width:700px;
        }
        h3{
                position:relative;
                left:450px;
                top:200px;
                width:700px;
                }
        .tableOut{
        position:relative;
        top:180px;
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

              <li class="nav-item" id="UserHomePage">
                       <a class="nav-link text-light" href="UserHomePage.jsp">Home</a>
                </li>
            </ul>
        </div>
    </nav>
    <h2>View Complaint Details:</h2>
        <h3 style="color:red">${msg} </h3>
<c:if test="${complaintDto.isEmpty()==false}">
<div class="tableOut">
            <table class="table">
              <thead class="thead-light">
                <tr>
                 <th scope="col">Id</th>
                  <th scope="col">ComplaintType</th>
                  <th scope="col">Country</th>
                  <th scope="col">State</th>
                  <th scope="col">City</th>
                  <th scope="col">Address</th>
                  <th scope="col">Description</th>
                  <th scope="col">Edit</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items="${complaintDto}" var="complaintDto">
                <tr>
                  <td scope="row">${complaintDto.getId()} </td>
                  <td>${complaintDto.getComplaintType()} </td>
                  <td>${complaintDto.getCountry()} </td>
                  <td>${complaintDto.getState()}</td>
                  <td>${complaintDto.getCity()}</td>
                  <td>${complaintDto.getAddress()}</td>
                  <td>${complaintDto.getDescription()}</td>
                  <td><a href="findByComplaintId?id=${complaintDto.id}&edit=edit">Edit</a></td>

                </tr>
                </c:forEach>
              </tbody>
            </table>
</div>
</c:if>
</body>
</html>
