 <%@ page isELIgnored="false" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Landing</title>
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
                       <a class="nav-link text-light" href="ResetPasswordAnyTime.jsp">ResetPasswordAnyTime</a>
                 </li>
                  <li class="nav-item" id="SignIn.jsp">
                       <a class="nav-link text-light" href="EditProfile.jsp">EditProfile</a>
                 </li>
                 <li class="nav-item" id="RaiseComplaint">
                       <a class="nav-link text-light" href="RaiseComplaint.jsp">RaiseComplaint</a>
                 </li>
                 <li>
             <img src="${pageContext.request.contextPath}${sessionScope.profileDTO}" width="70" height="70" value="" class="rounded-circle profile-image id=profileImage">
            </li>
            </ul>
        </div>
    </nav>
</body>
</html>
