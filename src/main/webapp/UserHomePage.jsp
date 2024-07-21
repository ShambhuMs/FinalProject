 <%@ page isELIgnored="false" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UserLanding</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <base href="http://localhost:8080/FinalProject/" />
    <style>
        body {
            background-color: #f0f2f5;
            margin: 0;
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
            height: 100%;
            width:1200px;
        }
        .btn{
          width:160px;
           position:relative;
            left:500px;
        }
         #profileImage{
          position:relative;
          left:900px;
        }
        #RaiseComplaint{
                position:relative;
                  left:850px;
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
                 <li class="nav-item" id="ResetPasswordAnyTime">
                       <a class="nav-link text-light" href="ResetPasswordAnyTime.jsp">ResetPassword</a>
                 </li>
                  <li class="nav-item" id="EditProfile">
                       <a class="nav-link text-light" href="EditProfile.jsp">EditProfile</a>
                 </li>
                 <li class="nav-item" id="RaiseComplaint">
                       <a class="nav-link text-light" href="RaiseComplaint.jsp">RaiseComplaint</a>
                 </li>
                     <li>
                    <a class="nav-link text-light" href="EditProfile.jsp"><img src="${pageContext.request.contextPath}${sessionScope.profileDTO}"
                     width="70" height="70" value="" class="rounded-circle profile-image" id="profileImage"> </a>
                     </li>
                 <li>
                  <form id="viewComplaint" action="viewComplaintDetails" method="post">
                        <button type="submit" class="btn btn-black btn-block text-white" id="submitBtn"  name="submit">ViewComplaints</button>
                     </form>
                 </l>
          </ul>
        </div>
    </nav>
    <h2 style="color:green">${msg} </h2>
</body>
</html>
