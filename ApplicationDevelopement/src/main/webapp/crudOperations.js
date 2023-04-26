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
	    var jsonData = JSON.parse(xhr.responseText);
	    console.log(jsonData);
	  } else {
	    console.log('Request failed.  Returned status of ' + xhr.status);
	  }
	};
	xhr.send();
	var table = document.getElementById("stdlist").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    newRow.insertCell(0).innerHTML = jsonData.id;
    newRow.insertCell(1).innerHTML = jsonData.firstName;
    newRow.insertCell(2).innerHTML = jsonData.lastName;
    newRow.insertCell(3).innerHTML = jsonData.email;
    newRow.insertCell(4).innerHTML = jsonData.phoneNo;
    newRow.insertCell(5).innerHTML = jsonData.age;
    newRow.insertCell(6).innerHTML = jsonData.gender;
    newRow.insertCell(7).innerHTML = jsonData.address;
    newRow.insertCell(8).innerHTML = jsonData.state;
    newRow.insertCell(9).innerHTML = jsonData.program;
    newRow.insertCell(10).innerHTML = jsonData.dept;
    cell13 = newRow.insertCell(11);
    cell13.innerHTML = `<a onClick="onEdit(this)" style="color:white;">Edit</a>
        <a onClick="onDelete(this)" style="color:white;">Delete</a>`;
}

// Insert New Record
function insertNewRecord(formdata) { //need to change
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "/ApplicationDevelopement/postDetail", true);
	xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
	xhr.onreadystatechange = function() {
  	if(xhr.readyState === 4 && xhr.status === 200) {
    	console.log(xhr.responseText);
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
    fetch(`http://localhost:8080/ApplicationDevelopement/getParticularDetail/`+id)
    .then(response => response.json())
    .then(data => {
		document.getElementById("id").value = data.id;
        document.getElementById("firstName").value = data.firstName;
        document.getElementById("lastName").value = data.lastName;
        document.getElementById("email").value = data.email;
        document.getElementById("phoneNo").value = data.phoneNo;
        document.getElementById("age").value = data.age;
        document.getElementById("gender").value = data.gender;
        document.getElementById("address").value = data.address;
        document.getElementById("state").value = data.state;
        document.getElementById("program").value = data.program;
        document.getElementById("dept").value = data.dept;
    })
    .catch(error => console.error(error))
}

// Update Record
function updateRecord(data) {
    alert("selected Row is: "+ selectedDataRow.rowIndex);
    selectedDataRow.cells[0].innerHTML = data.id;
    selectedDataRow.cells[1].innerHTML = data.firstName;
    selectedDataRow.cells[2].innerHTML = data.lastName;
    selectedDataRow.cells[3].innerHTML = data.email;
    selectedDataRow.cells[4].innerHTML = data.phoneNo;
    selectedDataRow.cells[5].innerHTML = data.age;
    selectedDataRow.cells[6].innerHTML = data.gender;
    selectedDataRow.cells[7].innerHTML = data.address;
    selectedDataRow.cells[8].innerHTML = data.state;
    selectedDataRow.cells[9].innerHTML = data.program;
    selectedDataRow.cells[10].innerHTML = data.dept;

    fetch('http://localhost:8080/ApplicationDevelopement/putDetail/'+data.id, {
    method: 'PUT',
    headers: {
    'Content-Type': 'application/json;charset=UTF-8'
    },
    body: JSON.stringify(data)
    })
    .then(response => response.json())
    .catch(error => console.error(error))
}

// Delete Function
function onDelete(td) {
    if (confirm('Are you sure ,you want to delete this record... ?')) {
        row = td.parentElement.parentElement;
        let id = row.cells[0].innerHTML;
        alert("deleting Appl.ID: "+ id);
        document.getElementById("stdlist").deleteRow(row.rowIndex);
        fetch('http://localhost:8080/ApplicationDevelopement/deleteDetail/'+id, {
        method:'DELETE',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
          }
        })
        .then(response => response.json()) 
        .then(response => console.log(response))
        resetForm();
    }
}