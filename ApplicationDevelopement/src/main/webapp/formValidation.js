//Error Message Function
function printErrorMsg(elementId, errorMsg)
{ 
    document.getElementById(elementId).innerHTML = errorMsg;
}


//Application ID Validation function
function isIDValid(id)
{
	let isValid = true;
	if(id == "")
	{
		isValid = false;
		printErrorMsg("noId","Please Enter Your Appl.ID");
	}
	else{
		isValid = true;
		printErrorMsg("noId","");
	}
	return isValid;
}

//firstName validation function
function isFirstNameValid(firstName)
{
	let isValid = true;
	if (firstName == "") {
        isValid = false;
        printErrorMsg("noFirstName","please enter your First Name"); 
    } else {
        let regex=/^[a-zA-Z\s]+$/; 
        if(regex.test(firstName) == false && firstName.length > 40){
            isValid = false;
            printErrorMsg("noFirstName","please enter a valid First Name (Only alphabets with maxLength:40"); 
        }
        else{
        	isValid = true;
            printErrorMsg("noFirstName","");
    	}
	}
	return isValid;
}

//lastName validation function
function isLastNamevalid(lastName)
{
	let isValid = true;
	if (lastName == "") {
        isValid = false;
        printErrorMsg("noLastName","please enter your Last Name"); 
    } else {
        let regex=/^[a-zA-Z\s]+$/; 
        if(regex.test(lastName) == false && lastName.length > 25){
            isValid = false;
            printErrorMsg("noLastName","please enter a valid Last Name (Only Alphabets and maxLength:25");
        }
        else{
        	isValid = true;
        	printErrorMsg("noLastName","");
    	}
	}
	return isValid;
}

//Email validation function
function isEmailValid(email)
{
	let isValid = true;
	if (email == "") {
        isValid = false;
        printErrorMsg("noEmail","please enter your Email Address");
    } else {
        let regex=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/; 
        if(regex.test(email) == false){
            isValid = false;
            printErrorMsg("noEmail","please enter a valid Email");
        }
        else{
        	isValid = true;
        	printErrorMsg("noEmail","");
    	}
	}
	return isValid;
}

//phoneNo validation function
function isPhoneNoValid(phoneNo)
{
	let isValid = true;
	if (phoneNo == "") {
        isValid = false;
        printErrorMsg("noPhoneNo","please enter your Mobile Number");
    } else {
        let regex= /^(0|91)?[6-9][0-9]{9}$/;
        if(regex.test(phoneNo) == false){
            isValid = false;
            printErrorMsg("noPhoneNo","please enter a valid Mobile number");
        }
        else{
        	isValid = true;
        	printErrorMsg("noPhoneNo","");
    	}
	}
	return isValid;
}

//Date Of Birth Validation Function
function isAgeValid(age)
{
	let isValid = true;
	if(age == "")
	{
		isValid = false;
		printErrorMsg("noAge","Please enter Your Date of Birth to proceed")
	}
	else{
        if(parseInt(age) >= 18 && parseInt(age) <= 24)
        {
            isValid = true;
            printErrorMsg("noAge","");
        }
        else{
            isValid = false;
            printErrorMsg("noAge","Your Age Should Be in range of 18-24 !!!");
        }
	}
	return isValid;
}

//Gender Validation
function isGendervalid(gender)
{
	let isValid = true;
	if(gender == "")
	{
		isValid = false;
		printErrorMsg("noGender","Please Select Your Gender");
	}
	else{
		isValid = true;
		printErrorMsg("noGender","");
	}
	return isValid;
}

//Address Validation function
function isAddressValid(address)
{
    let isValid = true;
    if(address == "")
    {
        isValid = false;
        printErrorMsg("noAddress","Please Enter Your Current Address");
    }
    else{
        isValid = true;
        printErrorMsg("noAddress","");
    }
    return isValid;
}

//State validation functon
function isStateValid(state)
{
    let isValid = true;
    if(state == "")
    {
        isValid = false;
        printErrorMsg("noState","Please Select your State");
    }
    else{
        isValid = true;
        printErrorMsg("noState","");
    }
    return isValid;
}

//progarm validation functon
function isprogramvalid(program)
{
    let isValid = true;
    if(program == "")
    {
        isValid = false;
        printErrorMsg("noProgram","Please Select your preferred program");
    }
    else{
        isValid = true;
        printErrorMsg("noProgram","");
    }
    return isValid;
}

//Dept validation functon
function isDeptValid(dept)
{
    let isValid = true;
    if(dept == "")
    {
        isValid = false;
        printErrorMsg("noDept","Please Select your Preferred Dept.");
    }
    else{
        isValid = true;
        printErrorMsg("noDept","");
    }
    return isValid;
}


//getters for student details
function getID()
{
	const id = document.getElementById("id").value;
	return id;
}

function getFirstName()
{
	const firstName = document.getElementById("firstName").value;
	return firstName;
}

function getLastname()
{
	const lastName = document.getElementById("lastName").value;
	return lastName;
}

function getEmail()
{
	const email = document.getElementById("email").value;
	return email;
}

function getPhoneNo()
{
	const phoneNo = document.getElementById("phoneNo").value;
    return phoneNo;
}

function getAge()
{ 
	const age = document.getElementById("age").value;
	return age;
}

function getGender()
{
	const gender = document.getElementById("gender").value;
	return gender;
}

function getAddress()
{
    const address = document.getElementById("address").value;
    return address;
}

function getState()
{
    const state = document.getElementById("state").value;
    return state;
}

function getProgram()
{
    const program = document.getElementById("program").value;
    return program;
}

function getDept()
{
    const dept = document.getElementById("dept").value;
    return dept;
}


// main validation function
function validate() 
{
	let id = getID();
    let firstName = getFirstName();
    let lastName = getLastname();
    let email = getEmail();
    let phoneNo = getPhoneNo();
    let age = getAge();
    let gender = getGender();
    let address = getAddress();
    let state = getState();
    let program = getProgram();
    let dept = getDept();
    
    let idValidity = isIDValid(id);
    let firstNameValidity = isFirstNameValid(firstName);
    let lastNameValidity = isLastNamevalid(lastName);
    let emailvalidity = isEmailValid(email);
    let phoneNovalidity = isPhoneNoValid(phoneNo);
    let ageValidity = isAgeValid(age);
    let genderValidity = isGendervalid(gender);
    let addressValidity = isAddressValid(address);
    let stateValidity = isStateValid(state);
    let programValidity = isprogramvalid(program);
    let deptValidity = isDeptValid(dept);
    
    //validate all of them
    if(idValidity && firstNameValidity && lastNameValidity && emailvalidity && phoneNovalidity && ageValidity && genderValidity && addressValidity && stateValidity && programValidity && deptValidity)
    {
		return true;
	}
	else
	{
		return false;
	}  
}