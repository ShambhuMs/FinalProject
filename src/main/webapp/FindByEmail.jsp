<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f0f2f5;
        }
        .login-form {
            width: 360px;
            margin: 100px auto;
            font-family: Arial, sans-serif;
        }
        .login-form form {
            margin-bottom: 15px;
            position:relative;
            top:120px;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(0,0,0,0.1);
        }
        .login-form h2 {
            margin: 0 0 15px;
        }
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .btn {
            font-size: 15px;
            font-weight: bold;
        }
        .login-link {
            text-align: center;
            margin-top: 140px;
        }
        .error {
            color: red;
            font-size: 12px;
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
         #home {
            position: relative;
            left: 1200px;
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
                <li class="nav-item" id="home">
                    <a class="nav-link text-light" href="index.jsp">Home</a>
                </li>
                <li class="nav-item" id="pass">
                    <a class="nav-link text-light" href="PasswordReset.jsp">Password</a>
                </li>
            </ul>
        </div>
    </nav>
<div class="login-form">
    <form action="fetchByEmail" method="get">
        <h4 class="text-center">Enter Your Email </h4>
       <span style="color:red">  ${msg} </span>
        <div class="form-group">
            <input type="text" class="form-control" placeholder=" email " id="email" name="email" required>
            <div id="error-email" class="error"></div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block" id="submitBtn" value="" name="submit">Generate Password</button>
        </div>
        <div class="clearfix">
<!--            <label class="float-left form-check-label"><input type="checkbox"> Remember me</label>
           <a href="FindByEmail.jsp" class="float-right">Forgot Password?</a> -->
        </div>
    </form>
    <p class="login-link">Don't have an account?
     <a href="Signup.jsp">Sign up here</a>
     </p>
</div>

<!-- <script>
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const submitBtn = document.getElementById('submitBtn');

    emailInput.addEventListener('input', function() {
        const inputValue = this.value.trim();
        const errorEmail = document.getElementById('error-email');

         if (!inputValue.includes('@') || !/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(inputValue) || inputValue.length<=8 || inputValue.length>=28 ) {
            errorEmail.textContent = 'Mail must be @, special characters, digits';
        } else {
            errorEmail.textContent = '';
        }
        validateForm();
    });

   passwordInput.addEventListener('input', function() {
       const inputValue = this.value.trim();
       const errorPassword = document.getElementById('error-password');

       const hasAlphabet = /[a-zA-Z]/.test(inputValue);
       const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(inputValue);
       const isValidLength = inputValue.length > 6 && inputValue.length < 18;

       if (!isValidLength) {
           errorPassword.textContent = 'Password length should be greater than 6 and less than 18';
       } else if (!hasAlphabet || !hasSpecialChar) {
           errorPassword.textContent = 'Password must contain alphabets and special characters';
       } else {
           errorPassword.textContent = '';
       }
       validateForm();
   });

    function validateForm() {
        const errorEmail = document.getElementById('error-email').textContent;
        const errorPassword = document.getElementById('error-password').textContent;
        if (errorEmail === '' && errorPassword === '') {
            submitBtn.removeAttribute('disabled');
        } else {
            submitBtn.setAttribute('disabled', 'disabled');
        }
    }
</script> -->
</body>
</html>
