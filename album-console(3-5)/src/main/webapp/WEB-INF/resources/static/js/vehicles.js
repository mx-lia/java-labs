function editVehicle(id) {
    document.location.href = `vehicles/${id}/edit`;
}

function deleteVehicle(id) {
    fetch(`/vehicles/${id}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.status === 204) {
            document.getElementById(id + '-tr').innerText = '';
        }
    })
}