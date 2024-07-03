<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
      <c:if test="${action=='edit'}">  <input type="hidden" name="id" value="${complaintDto.id}"> </c:if>
        <div class="form-group">
            <label for="complaintType">Complaint Type</label>
            <select class="form-control" id="complaintType" ${readOnly=="disable"? 'readonly' : ''} name="complaintType" onblur="setComplaintType()" required>
                        <option value="0"  ${complaintDto.complaintType == null ? 'selected' :''}>Choose...</option>
                        <option  value="Drainage Problem" ${complaintDto.complaintType eq 'Drainage Problem' ? 'selected' :''}>Drainage Problem</option>
                        <option  value="Electric Problem" ${complaintDto.complaintType eq 'Electric Problem' ? 'selected' :''}>Electric Problem</option>
                        <option  value="Plumber Problem" ${complaintDto.complaintType eq 'Plumber Problem' ? 'selected' :''}>Plumber Problem</option>
                        <option  value="Wastage Problem" ${complaintDto.complaintType eq 'Wastage Problem' ? 'selected' :''}>Wastage Problem</option>
                        <option  value="Water Problem" ${complaintDto.complaintType eq 'Water Problem' ? 'selected' :''}>Water Problem</option>
                 </select>
            <div id="error-complaintType" class="error"></div>
        </div>
       <div class="form-group">
           <label for="country">Country</label>
           <select class="form-control" ${readOnly=="disable"? 'readonly' : ''} id="country" name="country" value="" onchange="fetchStates(); validateCountry();" required>
            <c:if test="${action=='edit'}">   <option value="" >${complaintDto.country}</option> </c:if>
              <c:if test="${action!='edit'}"> <option value="" >Select Country</option></c:if>
           </select>
           <div id="error-country" class="error"></div>
       </div>
        <div class="form-group">
            <label for="state">State</label>
            <select class="form-control" ${readOnly=="disable"? 'readonly' : ''} id="state" name="state" value="" onblur="fetchCities(); validateState();" required >
            <c:if test="${action=='edit'}">     <option value="">${complaintDto.state}</option> </c:if>
             <c:if test="${action!='edit'}">  <option value="">Select State</option> </c:if>
            </select>
            <div id="error-state" class="error"></div>
        </div>
        <div class="form-group">
            <label for="city">City</label>
            <select class="form-control" id="city" ${readOnly=="disable"? 'readonly' : ''} name="city" onblur="validateCity()" required >
             <c:if test="${action=='edit'}">   <option value="${complaintDto.city}"> ${complaintDto.city}  </option> </c:if>
             <c:if test="${action!='edit'}">  <option value="0"  >Select City </option> </c:if>
            </select>
            <div id="error-city" class="error"></div>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" class="form-control" placeholder="Enter Address" id="address" name="address"
           value="${complaintDto.address}"  onblur="setAddress()" required/>
            <div id="error-address" class="error"></div>
        </div>
      <div class="mb-3">
                        <div class="form-floating">
                        <label for="description">Description</label>
                            <textarea class="form-control" placeholder="Leave a comment here" name="description"
                            id="description" oninput="updateDescriptionCount()" maxlength="300"
                            onblur="validateDescription()">${complaintDto.description}</textarea>
                        </div>
                        <span id="descriptionError" class="text-danger"></span>
                        <small id="descriptionCount" class="form-text text-muted">300 characters remaining</small>
                    </div>
                      <c:if test="${action == 'edit'}">
                             <div class="form-group">
                                        <input type="submit" class="btn btn-primary btn-block" id="updateBtn" value="Update" name="submit" >
                              </div>
                              </c:if>
        <c:if test="${action != 'edit'}">
        <div class="form-group">
            <input type="submit" class="btn btn-primary btn-block" id="submitBtn" value="Submit" name="submit" disabled>
        </div>
        </c:if>

    </form>
</div>

</body>
<script>
  let getFields = {
      "type": false,
      "address": false,
      "country": false,
      "state": false,
      "city": false,
      "description": false
  };

  document.addEventListener("DOMContentLoaded", function() {
      fetchCountries();
  });

  function validate() {
      let flag = false;
      for (let [key, value] of Object.entries(getFields)) {
          if (value === false) {
              flag = true;
              break;
          }
      }
      if (!flag) {
          document.getElementById("submitBtn").removeAttribute("disabled");
      } else {
          document.getElementById("submitBtn").setAttribute("disabled", "");
      }
  }

  function setAddress() {
      let name = document.getElementById("address");
      let error = document.getElementById("error-address");

      if (name.value.trim() === "" || name.value.length < 3 || name.value.length > 300) {
          error.innerHTML = "Please enter a valid Address (3-300 characters)";
          error.style.color = 'red';
          getFields["address"] = false;
      } else {
          getFields["address"] = true;
          error.innerHTML = "";
      }
      validate();
  }

  function updateDescriptionCount() {
      let description = document.getElementById("description");
      let count = 300 - description.value.length;
      document.getElementById("descriptionCount").innerText = count + " characters remaining";

      let error = document.getElementById("descriptionError");
      if (description.value.trim() === "" || description.value.length > 300) {
          error.innerHTML = "Please enter a valid Description (1-300 characters)";
          error.style.color = 'red';
          getFields["description"] = false;
      } else {
          getFields["description"] = true;
          error.innerHTML = "";
      }
      validate();
  }

 async function fetchCountries() {
     try {
         const response = await fetch("https://countriesnow.space/api/v0.1/countries");
         const data = await response.json();
         const countryDropdown = document.getElementById("country");
         data.data.forEach(country => {
             let option = document.createElement("option");
             option.value = country.country;
             option.text = country.country;
             countryDropdown.add(option);
         });
     } catch (error) {
         console.error("Error fetching countries: ", error);
     }
 }

  async function fetchStates() {
      const country = document.getElementById("country").value;
      if (country === "") {
          getFields["country"] = false;
          validate();
          return;
      }
      getFields["country"] = true;

      try {
          const response = await fetch("https://countriesnow.space/api/v0.1/countries/states", {
              method: "POST",
              headers: {
                  "Content-Type": "application/json"
              },
              body: JSON.stringify({ country: country })
          });
          const data = await response.json();
          const stateDropdown = document.getElementById("state");
          stateDropdown.innerHTML = '<option value="">Select State</option>';
          data.data.states.forEach(state => {
              let option = document.createElement("option");
              option.value = state.name;
              option.text = state.name;
              stateDropdown.add(option);
          });
      } catch (error) {
          console.error("Error fetching states: ", error);
      }
      validate();
  }

  function validateCountry() {
      let country = document.getElementById("country").value;
      let error = document.getElementById("error-country");

      if (country === "") {
          error.innerHTML = "Please select a country";
          error.style.color = 'red';
          getFields["country"] = false;
      } else {
          error.innerHTML = "";
          getFields["country"] = true;
      }
      fetchStates(); // Ensure this function is called after selecting a country
      validate();
  }


  async function fetchCities() {
      const country = document.getElementById("country").value;
      const state = document.getElementById("state").value;
      if (state === "0") {
          getFields["state"] = false;
          validate();
          return;
      }
      getFields["state"] = true;

      const response = await fetch("https://countriesnow.space/api/v0.1/countries/state/cities", {
          method: "POST",
          headers: {
              "Content-Type": "application/json"
          },
          body: JSON.stringify({ country: country, state: state })
      });
      const data = await response.json();
      const cityDropdown = document.getElementById("city");
      cityDropdown.innerHTML = '<option value="0">Choose...</option>';
      data.data.forEach(city => {
          let option = document.createElement("option");
          option.value = city;
          option.text = city;
          cityDropdown.add(option);
      });
      validate();
  }
  function setComplaintType(){
  let obj=document.getElementById("complaintType");
  let error=document.getElementById("error-complaintType");

  if(obj.value!="0"){
  error.innerHTML="";
  getFields["type"]=true;
  }
  else{
  error.innerHTML="Please select Complaint Type";
  error.style.color='red';
  getFields["type"]=false;
  }
  validate();
  }





      function validateState() {
          let state = document.getElementById("state").value;
          let error = document.getElementById("error-state");

          if (state === "0") {
              error.innerHTML = "Please select a state";
              error.style.color = 'red';
              getFields["state"] = false;
          } else {
              error.innerHTML = "";
              getFields["state"] = true;
          }
          validate();
      }

      function validateCity() {
          let city = document.getElementById("city").value;
          let error = document.getElementById("error-city");

          if (city === "0") {
              error.innerHTML = "Please select a city";
              error.style.color = 'red';
              getFields["city"] = false;
          } else {
              error.innerHTML = "";
              getFields["city"] = true;
          }
          validate();
      }

  function validateDescription() {
          let description = document.getElementById("description");
          let error = document.getElementById("error-description");

          if (description.value.trim() === "" || description.value.length > 300) {
              error.innerHTML = "Please enter a valid Description (1-300 characters)";
              error.style.color = 'red';
              getFields["description"] = false;
          } else {
              getFields["description"] = true;
              error.innerHTML = "";
          }
          validate();
      }
</script>
</html>
