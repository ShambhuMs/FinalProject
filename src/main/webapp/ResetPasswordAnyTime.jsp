<%@ page isELIgnored="false" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PasswordReset</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <base href="${pageContext.request.contextPath}">
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

                <li class="nav-item" id="home">
                <c:choose>
                        <c:when test="${departmentAdmin}">
                            <a class="nav-link text-light" href="DepartmentAdminHome.jsp">Home</a>
                        </c:when>
                        <c:when test="${!employee && !departmentAdmin}">
                            <a class="nav-link text-light" href="UserHomePage.jsp">Home</a>
                        </c:when>
                        <c:when test="${employee && !departmentAdmin}">
                             <a class="nav-link text-light" href="EmployeeHome.jsp">Home</a>
                        </c:when>
                  </c:choose>
                </li>
            </ul>
        </div>
    </nav>
    <div class="signup-form">
    <c:choose>
        <c:when test="${!employee && !departmentAdmin}">
            <form action="resetPasswordAnyTime" method="post">
        </c:when>
        <c:when test="${employee}">
            <form action="employee/resetPasswordAnyTime" method="post">
         </c:when>
         <c:when test="${departmentAdmin}">
               <form action="departmentAdmin/resetPasswordAnyTime" method="post">
         </c:when>
     </c:choose>
            <h2>Password Set</h2>
        <span style="color:green">${msg}</span>
        <span style="color:red">${errorMsg}</span>
            <div class="form-group">
          <c:choose>
             <c:when test="${!employee && !departmentAdmin}">
                <input type="text" ${readOnly=='disable' ? 'readonly': 'hidden'} class="form-control"
                     placeholder="Enter email" id="email" name="email" value="${dto.email}"  required>
             </c:when>
           </c:choose>
                <div id="error-email" class="error"> </div>
            </div>
             <div class="form-group">
               <label for="password">Password</label>
                <input type="password"   class="form-control" placeholder="Enter old password" id="password" name="password" value="" required>
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
    <script>
const emailInput = document.getElementById('email');
const newPasswordInput = document.getElementById('newPassword');
const confirmNewPasswordInput = document.getElementById('confirmNewPassword');
const submitBtn = document.getElementById('submitBtn');

newPasswordInput.addEventListener('input', function() {
    validateNewPassword();
    validateConfirmPassword();
    validateForm();
});

confirmNewPasswordInput.addEventListener('input', function() {
    validateConfirmPassword();
    validateForm();
});

function validateNewPassword() {
    const inputValue = newPasswordInput.value.trim();
    const errorNewPassword = document.getElementById('error-newPassword');

    const hasAlphabet = /[a-zA-Z]/.test(inputValue);
    const hasDigit = /[0-9]/.test(inputValue);
    const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(inputValue);
    const isValidLength = inputValue.length > 6 && inputValue.length < 18;

    if (!isValidLength) {
        errorNewPassword.textContent = 'Password length should be greater than 6 and less than 18';
        errorNewPassword.style.display = 'block';
    }else if(hasDigit){
             errorNewPassword.textContent = '';
             errorNewPassword.style.display = 'none';
    }
    else if (!hasAlphabet || !hasSpecialChar) {
        errorNewPassword.textContent = 'Password must contain alphabets and special characters';
        errorNewPassword.style.display = 'block';
    }  else{
        errorNewPassword.textContent = '';
        errorNewPassword.style.display = 'none';
    }
}

function validateConfirmPassword() {
    const newPasswordValue = newPasswordInput.value.trim();
    const confirmPasswordValue = confirmNewPasswordInput.value.trim();
    const errorConfirmNewPassword = document.getElementById('error-confirmNewPassword');

    if (confirmNewPasswordInput === document.activeElement || confirmPasswordValue.length > 0) {
        if (confirmPasswordValue !== newPasswordValue) {
            errorConfirmNewPassword.textContent = 'Passwords do not match';
            errorConfirmNewPassword.style.display = 'block';
        } else {
            errorConfirmNewPassword.textContent = '';
            errorConfirmNewPassword.style.display = 'none';
        }
    } else {
        errorConfirmNewPassword.textContent = '';
        errorConfirmNewPassword.style.display = 'none';
    }
}

function validateForm() {
    const errorNewPassword = document.getElementById('error-newPassword').textContent;
    const errorConfirmNewPassword = document.getElementById('error-confirmNewPassword').textContent;

    if (errorNewPassword === '' && errorConfirmNewPassword === '' && newPasswordInput.value.trim() !== '' && confirmNewPasswordInput.value.trim() !== '') {
        submitBtn.removeAttribute('disabled');
    } else {
        submitBtn.setAttribute('disabled', 'disabled');
    }
}

 </script>
</body>
</html>
