// Get Values From Form
function readFormData() { //no need to change...
    var formData = {};
    formData["id"] = getID();
    formData["firstName"] = getFirstName();
    formData["lastName"] = getLastname();
    formData["email"] = getEmail();
    formData["phoneNo"] = getPhoneNo();
    formData["age"] = getAge();
    formData["gender"] = getGender();
    formData['address'] = getAddress();
    formData["state"] = getState();
    formData['program'] = getProgram();
    formData['dept'] = getDept();
    return formData;
}

function getAllRecordsAndInsert()
{
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/ApplicationDevelopement/getAllDetails', true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
	if (xhr.status === 200) {
		var jsonArray = JSON.parse(xhr.responseText);
        for (var i = 0; i < jsonArray.length; i++) 
        {
        var jsonString = jsonArray[i];
        var Data = JSON.parse(jsonString);
	    var table = document.getElementById("stdlist").getElementsByTagName('tbody')[0];
	    var newRow = table.insertRow(table.length);
	    newRow.insertCell(0).innerHTML = Data.id;
	    newRow.insertCell(1).innerHTML = Data.firstName;
	    newRow.insertCell(2).innerHTML = Data.lastName;
	    newRow.insertCell(3).innerHTML = Data.email;
	    newRow.insertCell(4).innerHTML = Data.phoneNo;
	    newRow.insertCell(5).innerHTML = Data.age;
	    newRow.insertCell(6).innerHTML = Data.gender;
	    newRow.insertCell(7).innerHTML = Data.address;
	    newRow.insertCell(8).innerHTML = Data.state;
	    newRow.insertCell(9).innerHTML = Data.program;
	    newRow.insertCell(10).innerHTML = Data.dept;
	    cell13 = newRow.insertCell(11);
	    cell13.innerHTML = `<a onClick="onEdit(this)" style="color:white;">Edit</a>
	        <a onClick="onDelete(this)" style="color:white;">Delete</a>`;
	  }
	  } else {
	    console.log('Request failed.  Returned status of ' + xhr.status);
	  }
	};
	xhr.send();
}

// Insert New Record
function insertNewRecord(formdata) { //need to change
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/ApplicationDevelopement/postDetail", true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function() {
  	if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
    	console.log(xhr.responseText);
  	}else {
	    console.log('Request failed.  Returned status of ' + xhr.status);
	  }
	};
	xhr.send(JSON.stringify(formdata));
	var table = document.getElementById("stdlist").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    newRow.insertCell(0).innerHTML = formdata.id;
    newRow.insertCell(1).innerHTML = formdata.firstName;
    newRow.insertCell(2).innerHTML = formdata.lastName;
    newRow.insertCell(3).innerHTML = formdata.email;
    newRow.insertCell(4).innerHTML = formdata.phoneNo;
    newRow.insertCell(5).innerHTML = formdata.age;
    newRow.insertCell(6).innerHTML = formdata.gender;
    newRow.insertCell(7).innerHTML = formdata.address;
    newRow.insertCell(8).innerHTML = formdata.state;
    newRow.insertCell(9).innerHTML = formdata.program;
    newRow.insertCell(10).innerHTML = formdata.dept;
   	cell13 = newRow.insertCell(11);
    cell13.innerHTML = `<a onClick="onEdit(this)" style="color:white;">Edit</a>
        <a onClick="onDelete(this)" style="color:white;">Delete</a>`;
}

// Reset Function
function resetForm() { //no change needed
	document.getElementById("id").value = "";
    document.getElementById("firstName").value = "";
    document.getElementById("lastName").value = "";
    document.getElementById("email").value = "";
    document.getElementById("phoneNo").value = "";
    document.getElementById("age").value = "";
    document.getElementById("gender").value = "";
    document.getElementById('address').value = "";
    document.getElementById('state').value = "";
    document.getElementById('program').value = "";
    document.getElementById('dept').value = "";
    selectedDataRow = null;
}

// Edit Function
function onEdit(td) {  //need to change
    selectedDataRow = td.parentElement.parentElement;
    let id = selectedDataRow.cells[0].innerHTML;
    alert("selected Appl.ID is: "+ id);
    var xhr = new XMLHttpRequest();
	xhr.open('GET', "/ApplicationDevelopement/getParticularDetail?id="+id, true);
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onload = function() {
	  if (xhr.status === 200) {
	    var Data = JSON.parse(xhr.responseText);
	    console.log(Data);
	    document.getElementById("id").value = Data.id;
        document.getElementById("firstName").value = Data.firstName;
        document.getElementById("lastName").value = Data.lastName;
        document.getElementById("email").value = Data.email;
        document.getElementById("phoneNo").value = Data.phoneNo;
        document.getElementById("age").value = Data.age;
        document.getElementById("gender").value = Data.gender;
        document.getElementById("address").value = Data.address;
        document.getElementById("state").value = Data.state;
        document.getElementById("program").value = Data.program;
        document.getElementById("dept").value = Data.dept;
	  } else {
	    console.log('Request failed.  Returned status of ' + xhr.status);
	  }
	};
	xhr.send();
}

// Update Record
function updateRecord(formData) {
	
    alert("selected Row is: "+ selectedDataRow.rowIndex);
    let id = selectedDataRow.cells[0].innerHTML;
    var xhr = new XMLHttpRequest();
	xhr.open("PUT", "/ApplicationDevelopement/putDetail?id="+id, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function() {
  	if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
    	console.log(xhr.responseText);
  	}else {
	    console.log('Request failed.  Returned status of ' + xhr.status);
	  }
	};
	xhr.send(JSON.stringify(formData));
    selectedDataRow.cells[0].innerHTML = formData.id;
    selectedDataRow.cells[1].innerHTML = formData.firstName;
    selectedDataRow.cells[2].innerHTML = formData.lastName;
    selectedDataRow.cells[3].innerHTML = formData.email;
    selectedDataRow.cells[4].innerHTML = formData.phoneNo;
    selectedDataRow.cells[5].innerHTML = formData.age;
    selectedDataRow.cells[6].innerHTML = formData.gender;
    selectedDataRow.cells[7].innerHTML = formData.address;
    selectedDataRow.cells[8].innerHTML = formData.state;
    selectedDataRow.cells[9].innerHTML = formData.program;
    selectedDataRow.cells[10].innerHTML = formData.dept;
    alert("Data Updated !!!");
}

// Delete Function
function onDelete(td) {
    if (confirm('Are you sure ,you want to delete this record... ?')) {
        row = td.parentElement.parentElement;
        let recordId = row.cells[0].innerHTML;
        alert("deleting Appl.ID: "+ id);
        var xhr = new XMLHttpRequest();
		xhr.open('DELETE', "/ApplicationDevelopement/deleteRecord?id="+recordId, true);
		xhr.setRequestHeader('Content-type', 'application/json');
		xhr.onload = function() {
		  if (xhr.status === 200 || xhr.status === 204) {
		    console.log(xhr.responseText);
		  } else {
		    console.log('Request failed.  Returned status of ' + xhr.status);
		  }
		};
		xhr.send();
		document.getElementById("stdlist").deleteRow(row.rowIndex);
		alert("Record Deleted!!!");
        resetForm();
    }
}