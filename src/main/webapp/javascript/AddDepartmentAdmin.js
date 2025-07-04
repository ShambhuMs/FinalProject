document.addEventListener('DOMContentLoaded', function() {
    const nameInput = document.getElementById('depAdminName');
    const emailInput = document.getElementById('departmentAdminEmail');
    const phoneNumberInput = document.getElementById('departmentAdminPhoneNumber');
    const departmentSelect = document.getElementById('departmentType');
    const submitBtn = document.getElementById('submitBtn');

    const errorName = document.getElementById('error-depAdminName');
    const errorEmail = document.getElementById('error-depAdminEmail');
    const errorPhoneNumber = document.getElementById('error-departmentAdminPhoneNumber');
    const errorDepartment = document.getElementById('error-departmentType');

    function validateName() {
        const name = nameInput.value;
        if (name.length < 3 || name.length > 30 || /\d/.test(name)) {
            errorName.textContent = "Name must be between 3 and 30 characters and not contain numbers.";
            errorName.style.display = 'block';
        } else {
            errorName.textContent = "";
            errorName.style.display = 'none';
        }
        validateForm();
    }

    function validateEmail() {
        const email = emailInput.value.trim();
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

        if (!emailPattern.test(email)) {
            errorEmail.textContent = "Please enter a valid email address.";
            errorEmail.style.display = 'block';
            validateForm();
            return;
        }

        let xhr = new XMLHttpRequest();
        xhr.open("GET", `http:${pageContext.request.contextPath}/validateDepartmentAdminEmail?email=${encodeURIComponent(email)}`, true);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    let responseText = xhr.responseText.trim();
                    if (responseText !== "") {
                        errorEmail.textContent = responseText;
                        errorEmail.style.display = 'block';
                    } else {
                        errorEmail.textContent = "";
                        errorEmail.style.display = 'none';
                    }
                } else {
                    console.error('Error validating email:', xhr.statusText);
                }
                validateForm();
            }
        };

        xhr.send();
    }

    function validatePhoneNumber() {
        const phoneNumber = phoneNumberInput.value.trim();
        if (!/^\d{10}$/.test(phoneNumber)) {
            errorPhoneNumber.textContent = "Phone number must be 10 digits.";
            errorPhoneNumber.style.display = 'block';
            validateForm();
            return;
        } else {
            errorPhoneNumber.textContent = "";
            errorPhoneNumber.style.display = 'none';
        }

        let xhr = new XMLHttpRequest();
        xhr.open("GET", `http:${pageContext.request.contextPath}/validateDepartmentAdminPhone?phoneNumber=${encodeURIComponent(phoneNumber)}`, true);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    let phoneExists = xhr.responseText.trim();
                    if (phoneExists) {
                        errorPhoneNumber.textContent = "Phone number is already registered. Please use a different phone number.";
                        errorPhoneNumber.style.display = 'block';
                    } else {
                        errorPhoneNumber.textContent = "";
                        errorPhoneNumber.style.display = 'none';
                    }
                } else {
                    console.error('Error validating phone number:', xhr.statusText);
                }
                validateForm();
            }
        };

        xhr.send();
    }

    function validateDepartment() {
        if (departmentSelect.value === "") {
            errorDepartment.textContent = "Please select a department.";
            errorDepartment.style.display = 'block';
        } else {
            errorDepartment.textContent = "";
            errorDepartment.style.display = 'none';
        }
        validateForm();
    }

    function validateForm() {
        const isNameValid = errorName.textContent === "";
        const isEmailValid = errorEmail.textContent === "";
        const isPhoneNumberValid = errorPhoneNumber.textContent === "";
        const isDepartmentValid = errorDepartment.textContent === "";

        submitBtn.disabled = !(isNameValid && isEmailValid && isPhoneNumberValid && isDepartmentValid);
    }

    nameInput.addEventListener('input', validateName);
    emailInput.addEventListener('input', validateEmail);
    phoneNumberInput.addEventListener('input', validatePhoneNumber);
    departmentSelect.addEventListener('change', validateDepartment);

    validateForm();
});
