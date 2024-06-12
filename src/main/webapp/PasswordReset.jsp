<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
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
                <li class="nav-item" id="SignIn">
                    <a class="nav-link text-light" href="SignIn.jsp">SignIn</a>
                </li>
                <li class="nav-item" id="home">
                    <a class="nav-link text-light" href="index.jsp">Home</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="signup-form">
        <form id="contactForm" action="resetPassword" method="post">
            <h2>Password Set</h2>
        <span style="color:green">${msg}</span>
            <div class="form-group">
                <input type="text" ${readOnly=='disable' ? 'readonly': ' '} class="form-control" placeholder="Enter email" id="email" name="email" value="${dto.email}" required>
                <div id="error-email" class="error"> </div>
            </div>
             <div class="form-group">
                <label for="password">Password</label>
                <input type="password"  ${readOnly=='disable' ? 'readonly': ' '}  class="form-control" placeholder="Enter password" id="password" name="password" value="${dto.password}" required>
                <div id="error-password" class="error"></div>
            </div>
            <div class="form-group">
                <label for="newPassword">New password</label>
                <input type="password" class="form-control" placeholder="Enter New password" id="newPassword" name="newPassword"  required>
                <div id="error-newPassword" class="error"></div>
            </div>
            <div class="form-group">
                <label for="confirmNewPassword">Confirm New password</label>
                <input type="password" class="form-control" placeholder=" " id="confirmNewPassword" name="confirmNewPassword"  required>
                <div id="error-confirmNewPassword" class="error"></div>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary btn-block" id="submitBtn" value="Reset" name="submit" />
            </div>
        </form>
    </div>

<!-- <script>
    document.addEventListener('DOMContentLoaded', function() {
        const firstNameInput = document.getElementById('firstName');
        const lastNameInput = document.getElementById('lastName');
        const emailInput = document.getElementById('email');
        const phoneNumberInput = document.getElementById('phoneNumber');
        const signUpCheck = document.getElementById('signUpCheck');
        const submitBtn = document.getElementById('submitBtn');

        const errorFirstName = document.getElementById('error-firstName');
        const errorLastName = document.getElementById('error-lastName');
        const errorEmail = document.getElementById('error-email');
        const errorPhoneNumber = document.getElementById('error-phoneNumber');
        const errorSignUpCheck = document.getElementById('error-signUpCheck');

        function validateFirstName() {
            const firstName = firstNameInput.value;
            if (firstName.length < 3 || firstName.length > 15 || /\d/.test(firstName)) {
                errorFirstName.textContent = "First name must be between 3 and 15 characters and not contain numbers.";
                errorFirstName.style.display = 'block';
            } else {
                errorFirstName.textContent = "";
                errorFirstName.style.display = 'none';
            }
            validateForm();
        }

        function validateLastName() {
            const lastName = lastNameInput.value;
            if (lastName.length < 3 || lastName.length > 15 || /\d/.test(lastName)) {
                errorLastName.textContent = "Last name must be between 3 and 15 characters and not contain numbers.";
                errorLastName.style.display = 'block';
            } else {
                errorLastName.textContent = "";
                errorLastName.style.display = 'none';
            }
            validateForm();
        }

        function validateEmail() {
            const email = emailInput.value;
            const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
            if (!emailPattern.test(email)) {
                errorEmail.textContent = "Please enter a valid email address.";
                errorEmail.style.display = 'block';
            } else {
                errorEmail.textContent = "";
                errorEmail.style.display = 'none';
            }
            validateForm();
        }

        function validatePhoneNumber() {
            const phoneNumber = phoneNumberInput.value;
            if (!/^\d{10}$/.test(phoneNumber)) {
                errorPhoneNumber.textContent = "Phone number must be 10 digits.";
                errorPhoneNumber.style.display = 'block';
            } else {
                errorPhoneNumber.textContent = "";
                errorPhoneNumber.style.display = 'none';
            }
            validateForm();
        }

        function validateCheckbox() {
            if (!signUpCheck.checked) {
                errorSignUpCheck.textContent = "You must agree to sign up for the newsletter.";
                errorSignUpCheck.style.display = 'block';
            } else {
                errorSignUpCheck.textContent = "";
                errorSignUpCheck.style.display = 'none';
            }
            validateForm();
        }

        function validateForm() {
            const isFirstNameValid = errorFirstName.textContent === "";
            const isLastNameValid = errorLastName.textContent === "";
            const isEmailValid = errorEmail.textContent === "";
            const isPhoneNumberValid = errorPhoneNumber.textContent === "";
            const isCheckboxValid = signUpCheck.checked;

            submitBtn.disabled = !(isFirstNameValid && isLastNameValid && isEmailValid && isPhoneNumberValid && isCheckboxValid);
        }

        firstNameInput.addEventListener('input', validateFirstName);
        lastNameInput.addEventListener('input', validateLastName);
        emailInput.addEventListener('input', validateEmail);
        phoneNumberInput.addEventListener('input', validatePhoneNumber);
        signUpCheck.addEventListener('change', validateCheckbox);

        validateForm();
    });
</script> -->
</body>
</html>
