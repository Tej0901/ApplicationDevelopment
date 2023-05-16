var selectedDataRow = null

function onFormSubmit() 
{
    if (validate()) {
        var formData = readFormData();
        if (selectedDataRow == null)
        {
            insertNewRecord(formData);
        }
        else{
            updateRecord(formData);
        }
        resetForm();
    }
    else{
        alert("Incorrect Entries!!!");
    }
}
