let parkingAreas = [];

async function loadParkingAreas() {
    try {
        const response = await fetch(`${API_URL}/parking-area/all`, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
                'Accept': 'application/json'
            }
        });
        
        const data = await response.json();
        
        if (response.ok && data.isSuccess) {
            parkingAreas = data.response;
            displayParkingAreas(parkingAreas);
            updateParkingAreaSelect();
        } else {
            throw new Error(data.message || 'Failed to load parking areas');
        }
    } catch (error) {
        console.error('Error loading parking areas:', error);
        alert('Error loading parking areas: ' + error.message);
    }
}

function displayParkingAreas(areas) {
    const tbody = document.getElementById('parkingAreasTableBody');
    tbody.innerHTML = '';
    
    if (!areas || areas.length === 0) {
        tbody.innerHTML = '<tr><td colspan="5" class="text-center">No parking areas available.</td></tr>';
        return;
    }
    
    areas.forEach(area => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${area.name}</td>
            <td>${area.location}</td>
            <td>${area.city}</td>
            <td>${area.capacity}</td>
            <td>
                <button class="btn btn-sm btn-primary btn-action" onclick="showEditModal('${area.id}', ${area.capacity})">Edit</button>
                <button class="btn btn-sm btn-danger btn-action" onclick="deleteParkingArea('${area.id}')">Delete</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

async function handleCreateParkingArea(event) {
    event.preventDefault();
    
    const capacity = parseInt(document.getElementById('capacity').value);
    if (capacity < 1 || capacity > 1000) {
        alert('Capacity must be between 1 and 1000');
        return;
    }

    const parkingAreaData = {
        name: document.getElementById('parkName').value,
        location: document.getElementById('location').value,
        city: document.getElementById('city').value,
        capacity: capacity,
        priceListRequest: {
            name: document.getElementById('priceListName').value,
            prices: [
                {
                    lowerBound: 0,
                    upperBound: 2,
                    cost: parseInt(document.getElementById('cost').value)
                }
            ]
        }
    };

    try {
        const response = await fetch(`${API_URL}/parking-area`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            body: JSON.stringify(parkingAreaData)
        });

        const data = await response.json();

        if (response.ok && data.isSuccess) {
            alert('Parking area created successfully!');
            event.target.reset();
            loadParkingAreas();
        } else {
            throw new Error(data.message || 'Failed to create parking area');
        }
    } catch (error) {
        console.error('Error creating parking area:', error);
        alert('Failed to create parking area: ' + error.message);
    }
}

function showEditModal(parkingAreaId, currentCapacity) {
    document.getElementById('editParkingAreaId').value = parkingAreaId;
    document.getElementById('editCapacity').value = currentCapacity;
    new bootstrap.Modal(document.getElementById('editModal')).show();
}

async function updateParkingArea() {
    const parkingAreaId = document.getElementById('editParkingAreaId').value;
    const newCapacity = parseInt(document.getElementById('editCapacity').value);

    if (newCapacity < 1 || newCapacity > 1000) {
        alert('Capacity must be between 1 and 1000');
        return;
    }

    try {
        const response = await fetch(`${API_URL}/parking-area/${parkingAreaId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            body: JSON.stringify({ capacity: newCapacity })
        });

        const data = await response.json();

        if (response.ok && data.isSuccess) {
            alert('Parking area updated successfully!');
            bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
            loadParkingAreas();
        } else {
            throw new Error(data.message || 'Failed to update parking area');
        }
    } catch (error) {
        console.error('Error updating parking area:', error);
        alert('Failed to update parking area: ' + error.message);
    }
}

async function deleteParkingArea(parkingAreaId) {
    if (confirm('Are you sure you want to delete this parking area?')) {
        try {
            const response = await fetch(`${API_URL}/parking-area/${parkingAreaId}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            });

            const data = await response.json();

            if (response.ok && data.isSuccess) {
                alert('Parking area deleted successfully!');
                loadParkingAreas();
            } else {
                throw new Error(data.message || 'Failed to delete parking area');
            }
        } catch (error) {
            console.error('Error deleting parking area:', error);
            alert('Failed to delete parking area: ' + error.message);
        }
    }
}

function searchParkingAreas() {
    const searchTerm = document.getElementById('searchInput').value.toLowerCase();
    const filteredAreas = parkingAreas.filter(area => 
        area.name.toLowerCase().includes(searchTerm) ||
        area.location.toLowerCase().includes(searchTerm) ||
        area.city.toLowerCase().includes(searchTerm)
    );
    displayParkingAreas(filteredAreas);
}

function updateParkingAreaSelect() {
    const select = document.getElementById('parkingAreaSelect');
    select.innerHTML = '<option value="">Select Parking Area</option>';
    
    if (parkingAreas && parkingAreas.length > 0) {
        parkingAreas.forEach(area => {
            const option = document.createElement('option');
            option.value = area.id;
            option.textContent = area.name;
            select.appendChild(option);
        });
    }
}

function showParkingAreas() {
    document.getElementById('parkingAreasList').style.display = 'block';
    document.getElementById('createParkingAreaForm').style.display = 'block';
    document.getElementById('incomeSection').style.display = 'none';
}

function showIncomeSection() {
    document.getElementById('parkingAreasList').style.display = 'none';
    document.getElementById('createParkingAreaForm').style.display = 'none';
    document.getElementById('incomeSection').style.display = 'block';
}

async function getIncome() {
    const parkingAreaId = document.getElementById('parkingAreaSelect').value;
    const dateInput = document.getElementById('incomeDate').value;
    
    if (!parkingAreaId || !dateInput) {
        alert('Please select parking area and date!');
        return;
    }

    try {
        const selectedDate = new Date(dateInput);
        selectedDate.setHours(23, 59, 59, 999);
        
        const now = new Date();
        now.setHours(23, 59, 59, 999);

        const selectedDateStr = selectedDate.toISOString().split('T')[0];
        const nowStr = now.toISOString().split('T')[0];

        if (selectedDateStr > nowStr) {
            alert('Income report cannot be viewed for future dates!');
            return;
        }

        const day = selectedDate.getDate().toString().padStart(2, '0');
        const month = (selectedDate.getMonth() + 1).toString().padStart(2, '0');
        const year = selectedDate.getFullYear();
        const formattedDate = `${day}-${month}-${year}`;

        const response = await fetch(`${API_URL}/parking-area/income?parkingAreaId=${parkingAreaId}&date=${formattedDate}`, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
                'Accept': 'application/json'
            }
        });
        
        const data = await response.json();
        
        if (!data.isSuccess) {
            document.getElementById('totalIncome').textContent = '0.00';
            if (data.message && !data.message.includes('No Daily Income')) {
                throw new Error(data.message);
            }
            return;
        }

        const income = data.response?.income || 0;
        document.getElementById('totalIncome').textContent = income.toFixed(2);
    } catch (error) {
        console.error('Error getting income information:', error);
        document.getElementById('totalIncome').textContent = '0.00';
        alert('Failed to get income information: ' + error.message);
    }
} 