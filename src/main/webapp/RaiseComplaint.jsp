<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Raise Complaint</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
        <form id="contactForm" action="raiseComplaint" method="post">
     <span style="color:red">
       <c:forEach items="${errorMessage}" var="error">
        ${error.defaultMessage}
        </c:forEach>
        ${failedMessage}
        </span>

        <span style="color:green">${successMessage}</span>
            <h2>Raise Complaint</h2>
            <div class="form-group">
                <label for="complaintType">Complaint Type</label>
                <input type="text" class="form-control" placeholder="Enter Complaint Type" id="complaintType" name="complaintType" value="${atmDTO.complaintType}" required>
                <div id="error-complaintType" class="error"></div>
            </div>
            <div class="form-group">
                <label for="country">Country</label>
                <input type="text" class="form-control" placeholder="Enter Country Name" id="country" name="country" value="${atmDTO.country}" required>
                <div id="error-country" class="error"></div>
            </div>
            <div class="form-group">
                <label for="state">State</label>
                <input type="text" class="form-control" placeholder="Enter state" id="state" name="state"  value="${atmDTO.state}" required>
                <div id="error-state" class="error"> </div>
            </div>
            <div class="form-group">
                <label for="city">City</label>
                <input type="text" class="form-control" placeholder="Enter city" id="city" name="city" value="${atmDTO.city}" required>
                <div id="error-city" class="error">
                </div>
            </div>
           <div class="form-group">
                <label for="address">Address</label>
                <input type="text" class="form-control" placeholder="Enter address" id="address" name="address" value="${atmDTO.address}" required>
                <div id="error-address" class="error">
                </div>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea class="form-control" placeholder="Enter Description" id="description" name="description" value="${atmDTO.description}" required></textarea>
                <div id="error-description" class="error">
                </div>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary btn-block" id="submitBtn" value="Submit" name="submit" />
            </div>
        </form>
    </div>
</body>
</html>
