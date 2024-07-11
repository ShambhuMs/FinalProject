<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="/FinalProject/script/Signup.js"></script>
    <style>
        body {
            background-color: #f0f2f5;
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
        .signup-form h2 {
            margin: 0 0 15px;
            text-align: center;
        }
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .btn {
            font-size: 15px;
            font-weight: bold;
        }
        .error {
            color: red;
            font-size: 12px;
            display: none;
        }
        #home {
            position: relative;
            left: 1200px;
        }
        h2 {
            color: black;
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
                <li class="nav-item" id="Login">
                    <a class="nav-link text-light" href="SignIn.jsp">SignIn</a>
                </li>
                <li class="nav-item" id="home">
                    <a class="nav-link text-light" href="index.jsp">Home</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="signup-form">
        <form id="contactForm" action="sign" method="post">
     <span style="color:red">
       <c:forEach items="${errorMessage}" var="error">
        ${error.defaultMessage}
        </c:forEach>
        </span>
        <span style="color:green">${msg}</span>
            <h2>Sign Up</h2>
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input type="text" class="form-control" placeholder="Enter First Name" id="firstName" name="firstName" value="${atmDTO.firstName}" required>
                <div id="error-firstName" class="error"></div>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text" class="form-control" placeholder="Enter Last Name" id="lastName" name="lastName" value="${atmDTO.lastName}" required>
                <div id="error-lastName" class="error"></div>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" placeholder="Enter email" id="email" name="email"  value="${atmDTO.email}" required>
                <div id="error-email" class="error"> </div>
              <div>  <span style="color:red">${failedEmailMsg}</span> </div>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone Number</label>
                <input type="text" class="form-control" placeholder="Enter Phone Number" id="phoneNumber" name="phoneNumber" value="${atmDTO.phoneNumber}" required>
                <div id="error-phoneNumber" class="error">
                </div>
             <div> <span style="color:red">${failedPhNoMsg}</span> </div>
            </div>
            <div class="form-group form-check mb-3">
                <input type="checkbox" class="form-check-input" id="signUpCheck">
                <label class="form-check-label" for="signUpCheck">Sign up for newsletter</label>
                <div id="error-signUpCheck" class="error"></div>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary btn-block" id="submitBtn" value="Submit" name="submit" disabled/>
            </div>
        </form>
    </div>
</body>
</html>
