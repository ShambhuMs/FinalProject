<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Raise Complaint</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<svg fill="#000000" width="64px" height="64px"
viewBox="-14.08 -14.08 92.16 92.16" id="Layer_1_1_" version="1.1" xml:space="preserve" xmlns="http://www.w3.org/2000/svg"
xmlns:xlink="http://www.w3.org/1999/xlink"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier"
stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g>
<path d="M32,17c-8.271,0-15,6.729-15,15s6.729,15,15,15s15-6.729,15-15S40.271,17,32,17z M32,45c-3.675,0-6.992-1.54-9.359-4 h18.718C38.992,43.46,35.675,45,32,45z M30.503,30.581C29.562,30.033,29,29.068,29,28v-2c0-0.838,0.355-1.645,0.974-2.212 c0.626-0.575,1.438-0.851,2.298-0.776C33.801,23.145,35,24.548,35,26.208V28c0,1.068-0.562,2.033-1.503,2.581L33,30.87v2.884 l4.84,1.383C39.112,35.5,40,36.678,40,38v1H24v-1c0-1.322,0.888-2.5,2.16-2.863L31,33.754V30.87L30.503,30.581z M42,40.295V38 c0-2.21-1.484-4.179-3.61-4.786L35,32.246v-0.266c1.246-0.939,2-2.416,2-3.979v-1.792c0-2.688-2.001-4.966-4.556-5.188 c-1.401-0.125-2.793,0.35-3.822,1.294C27.591,23.259,27,24.603,27,26v2c0,1.563,0.754,3.041,2,3.979v0.266l-3.39,0.969 C23.484,33.821,22,35.79,22,38v2.295c-1.872-2.253-3-5.144-3-8.295c0-7.168,5.832-13,13-13s13,5.832,13,13 C45,35.151,43.872,38.042,42,40.295z"></path>
<path d="M54,7h-9.184C44.402,5.839,43.302,5,42,5h-3.383C37.321,2.529,34.799,1,32,1c-2.799,0-5.321,1.528-6.617,4H22 c-1.302,0-2.402,0.839-2.816,2H10c-1.654,0-3,1.346-3,3v50c0,1.654,1.346,3,3,3h44c1.654,0,3-1.346,3-3V10C57,8.346,55.654,7,54,7z M51,49h-8v8H13V13h6v2h26v-2h6V49z M49.586,51L45,55.586V51H49.586z M21,8c0-0.551,0.449-1,1-1h4.618l0.487-0.975 C28.039,4.159,29.914,3,32,3s3.961,1.159,4.895,3.024L37.382,7H42c0.551,0,1,0.449,1,1v5H21V8z M55,60c0,0.551-0.449,1-1,1H10 c-0.551,0-1-0.449-1-1V10c0-0.551,0.449-1,1-1h9v2h-8v48h33.414L53,50.414V11h-8V9h9c0.551,0,1,0.449,1,1V60z"></path>
<rect height="2" width="2" x="39" y="49"></rect> <rect height="2" width="22" x="15" y="49"></rect> <rect height="2" width="22" x="15" y="53"></rect> <rect height="2" width="2" x="39" y="53"></rect>
 <path d="M32,5c-1.654,0-3,1.346-3,3s1.346,3,3,3s3-1.346,3-3S33.654,5,32,5z M32,9c-0.551,0-1-0.449-1-1s0.449-1,1-1s1,0.449,1,1 S32.551,9,32,9z"></path>
<rect height="2" width="2" x="24" y="9"></rect> <rect height="2" width="2" x="38" y="9"></rect> </g> </g></svg>
 <style>
        body {
            background-color: #f0f2f5;
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
        }
        .navbar {
            background-color: #3b5998;
        }
        .navbar-brand, .nav-link {
            color: #fff !important;
        }
        .signup-form {
            width: 400px;
            margin: 50px auto;
            padding: 40px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .signup-form h2 {
            margin-bottom: 20px;
            color: #333;
            text-align: center;
        }
        .form-group label {
            color: #333;
        }
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .btn-primary {
            background-color: #1877f2;
            border-color: #1877f2;
        }
        .btn-primary:hover {
            background-color: #155db2;
            border-color: #155db2;
        }
        .error {
            color: red;
            font-size: 12px;
            display: none;
        }
        .text-center {
            text-align: center;
        }
        .text-green {
            color: green;
        }
        #UserHomePage{
        position:relative;
           left:1100px;
           margin-top:10px;
        }
         #profileImage{
          position:relative;
          left:1120px;
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
                <a class="nav-link text-light" href="UserHomePage.jsp">UserHomePage</a>
            </li>
              <li>
                   <img src="${pageContext.request.contextPath}${sessionScope.profileDTO}" width="70" height="70" value="" class="rounded-circle profile-image" id="profileImage">
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
        <span class="text-green">${successMessage}</span>
        <h2>Raise Complaint</h2>
        <div class="form-group">
            <label for="complaintType">Complaint Type</label>
            <select class="form-control" id="complaintType" name="complaintType" required>
                <option value="">Select Complaints</option>
                <option value="electricity">Electricity</option>
                <option value="water">Water</option>
                <!-- Add other options as needed -->
            </select>
            <div id="error-complaintType" class="error"></div>
        </div>
        <div class="form-group">
            <label for="country">Country</label>
            <select class="form-control" id="country" name="country" onchange="loadStates()" required>
                <option value="">Select Country</option>
            </select>
            <div id="error-country" class="error"></div>
        </div>
        <div class="form-group">
            <label for="state">State</label>
            <select class="form-control" id="state" name="state" onchange="loadCities()" required disabled>
                <option value="">Select State</option>
            </select>
            <div id="error-state" class="error"></div>
        </div>
        <div class="form-group">
            <label for="city">City</label>
            <select class="form-control" id="city" name="city" required disabled>
                <option value="">Select City</option>
            </select>
            <div id="error-city" class="error"></div>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" class="form-control" placeholder="Enter Address" id="address" name="address" required>
            <div id="error-address" class="error"></div>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <textarea class="form-control" placeholder="Enter Description" id="description" name="description" required></textarea>
            <div id="error-description" class="error"></div>
            <div id="description-counter" class="text-muted">Description 0 of 1000 characters</div>
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary btn-block" id="submitBtn" value="Submit" name="submit" >
        </div>
    </form>
</div>

</body>
<script>
                var config = {
                        countriesUrl: 'https://api.countrystatecity.in/v1/countries',
                        statesUrl: 'https://api.countrystatecity.in/v1/countries/[ciso]/states',
                        citiesUrl: 'https://api.countrystatecity.in/v1/countries/[ciso]/states/[siso]/cities',
                        ckey: 'ODIzTTVJUGVIVWdQT1ZJbk1McU50RnJ0ZmtTUVNBcERseFdTb25SSA=='
                    }

                    var countrySelect = document.querySelector('#country'),
                        stateSelect = document.querySelector('#state'),
                        citySelect = document.querySelector('#city')

                    function loadCountries() {
                        fetch(config.countriesUrl, { headers: { "X-CSCAPI-KEY": config.ckey } })
                            .then(response => response.json())
                            .then(data => {
                                data.forEach(country => {
                                    const option = document.createElement('option')
                                    option.value = country.iso2
                                    option.textContent = country.name
                                    countrySelect.appendChild(option)
                                })
                            })
                            .catch(error => console.error('Error loading countries:', error))

                        stateSelect.disabled = true
                        citySelect.disabled = true
                        stateSelect.style.pointerEvents = 'none'
                        citySelect.style.pointerEvents = 'none'
                    }

                    function loadStates() {
                        stateSelect.disabled = false
                        citySelect.disabled = true
                        stateSelect.style.pointerEvents = 'auto'
                        citySelect.style.pointerEvents = 'none'

                        const selectedCountryCode = countrySelect.value
                        stateSelect.innerHTML = '<option value="">Select State</option>'
                        citySelect.innerHTML = '<option value="">Select City</option>'

                        fetch(config.statesUrl.replace('[ciso]', selectedCountryCode), { headers: { "X-CSCAPI-KEY": config.ckey } })
                            .then(response => response.json())
                            .then(data => {
                                data.forEach(state => {
                                    const option = document.createElement('option')
                                    option.value = state.iso2
                                    option.textContent = state.name
                                    stateSelect.appendChild(option)
                                })
                            })
                            .catch(error => console.error('Error loading states:', error))
                    }

                    function loadCities() {
                        citySelect.disabled = false
                        citySelect.style.pointerEvents = 'auto'

                        const selectedCountryCode = countrySelect.value
                        const selectedStateCode = stateSelect.value

                        citySelect.innerHTML = '<option value="">Select City</option>'

                        fetch(config.citiesUrl.replace('[ciso]', selectedCountryCode).replace('[siso]', selectedStateCode), { headers: { "X-CSCAPI-KEY": config.ckey } })
                            .then(response => response.json())
                            .then(data => {
                                data.forEach(city => {
                                    const option = document.createElement('option')
                                    option.value = city.iso2
                                    option.textContent = city.name
                                    citySelect.appendChild(option)
                                })
                            })
                            .catch(error => console.error('Error loading cities:', error));
                    }
                   window.onload = loadCountries;



</script>
</html>
