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
    <form action="signIn" method="post">
        <h2 class="text-center">Login</h2>
       <span style="color:red">  ${msg} </span>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Email or Phone" id="emailOrPhone" name="email" required>
            <div id="error-emailOrPhone" class="error"></div>
        </div>
        <div class="form-group">
            <input type="password" class="form-control" placeholder="Password" id="password" name="password" required>
            <div id="error-password" class="error"></div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block" id="submitBtn" value="" name="submit">Login</button>
        </div>
        <div class="clearfix">
<!--            <label class="float-left form-check-label"><input type="checkbox"> Remember me</label>-->
           <a href="#" class="float-right">Forgot Password?</a>
        </div>
    </form>
    <p class="login-link">Don't have an account?
     <a href="Signup.jsp">Sign up here</a>
     </p>
</div>

<!-- <script>
    const emailOrPhoneInput = document.getElementById('emailOrPhone');
    const passwordInput = document.getElementById('password');
    const submitBtn = document.getElementById('submitBtn');

    emailOrPhoneInput.addEventListener('input', function() {
        const inputValue = this.value.trim();
        const errorEmailOrPhone = document.getElementById('error-emailOrPhone');

        if (/^\d+$/.test(inputValue)) {
            if (inputValue.length === 10) {
                errorEmailOrPhone.textContent = '';
                this.blur(); // Turn off focus
            } else {
                errorEmailOrPhone.textContent = 'Please enter 10 digit number';
            }
        } else if (!inputValue.includes('@') || !/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(inputValue) || inputValue.length<=8 || inputValue.length>=28) {
            errorEmailOrPhone.textContent = 'Mail must be @, special characters, digits';
        } else {
            errorEmailOrPhone.textContent = '';
        }

        validateForm();
    });

    passwordInput.addEventListener('input', function() {
        const inputValue = this.value.trim();
        const errorPassword = document.getElementById('error-password');

        if (inputValue.length < 6 || inputValue.length > 18) {
            errorPassword.textContent = 'Password length should be greater than 6 and less than 18';
        } else if (!/[A-Z]/.test(inputValue) || !/[\W_]/.test(inputValue) || !/\d/.test(inputValue) || !/[a-z]/.test(inputValue)) {
            errorPassword.textContent = 'Password must contain Capital letter, Special character, Digit, and Text';
        } else {
            errorPassword.textContent = '';
        }

        validateForm();
    });

    function validateForm() {
        const errorEmailOrPhone = document.getElementById('error-emailOrPhone').textContent;
        const errorPassword = document.getElementById('error-password').textContent;

        if (errorEmailOrPhone === '' && errorPassword === '') {
            submitBtn.removeAttribute('disabled');
        } else {
            submitBtn.setAttribute('disabled', 'disabled');
        }
    }
</script> -->
</body>
</html>
