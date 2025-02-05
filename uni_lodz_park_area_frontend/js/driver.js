let parkingAreas = [];
let userVehicles = [];

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
            updateParkingAreaSelects();
        } else {
            throw new Error(data.message || 'Failed to load parking areas');
        }
    } catch (error) {
        console.error('Error loading parking areas:', error);
        alert('Error loading parking areas: ' + error.message);
    }
}

function updateParkingAreaSelects() {
    const checkInSelect = document.getElementById('checkInParkingArea');
    const checkOutSelect = document.getElementById('checkOutParkingArea');
    
    const options = '<option value="">Select Parking Area</option>' + 
        parkingAreas.map(area => `<option value="${area.id}">${area.name}</option>`).join('');
    
    checkInSelect.innerHTML = options;
    checkOutSelect.innerHTML = options;
}

async function loadUserVehicles() {
    try {
        const userId = localStorage.getItem('userId');
        const response = await fetch(`${API_URL}/vehicles/user/${userId}`, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
                'Accept': 'application/json'
            }
        });
        
        const data = await response.json();
        
        if (response.ok && data.isSuccess) {
            userVehicles = data.response;
            updateVehicleSelects();
        } else {
            throw new Error(data.message || 'Failed to load vehicles');
        }
    } catch (error) {
        console.error('Error loading vehicles:', error);
        alert('Error loading vehicles: ' + error.message);
    }
}

function updateVehicleSelects() {
    const checkInPlate = document.getElementById('checkInLicensePlate');
    const checkOutPlate = document.getElementById('checkOutLicensePlate');
    
    const options = '<option value="">Select License Plate</option>' + 
        userVehicles.map(vehicle => `<option value="${vehicle.licensePlate}" data-type="${vehicle.vehicleType}">${vehicle.licensePlate}</option>`).join('');
    
    checkInPlate.innerHTML = options;
    checkOutPlate.innerHTML = options;

    // Auto-fill vehicle type when plate is selected
    checkInPlate.onchange = function() {
        const selectedOption = this.options[this.selectedIndex];
        if (selectedOption.value) {
            document.getElementById('checkInVehicleType').value = selectedOption.dataset.type;
        }
    };

    checkOutPlate.onchange = function() {
        const selectedOption = this.options[this.selectedIndex];
        if (selectedOption.value) {
            document.getElementById('checkOutVehicleType').value = selectedOption.dataset.type;
        }
    };
}

async function handleAddVehicle(event) {
    event.preventDefault();
    
    const licensePlate = document.getElementById('licensePlate').value;
    const vehicleType = document.getElementById('vehicleType').value;
    const userId = localStorage.getItem('userId');

    try {
        const response = await fetch(`${API_URL}/vehicles/assign/${userId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            body: JSON.stringify({
                licensePlate,
                vehicleType
            })
        });

        if (response.ok) {
            alert('Vehicle added successfully!');
            event.target.reset();
            await loadUserVehicles();
        } else {
            const error = await response.json();
            alert('Failed to add vehicle: ' + error.message);
        }
    } catch (error) {
        alert('An error occurred: ' + error.message);
    }
}

async function handleCheckIn(event) {
    event.preventDefault();
    
    const parkingAreaId = document.getElementById('checkInParkingArea').value;
    const licensePlate = document.getElementById('checkInLicensePlate').value;
    const vehicleType = document.getElementById('checkInVehicleType').value;
    const userId = localStorage.getItem('userId');

    try {
        const detailResponse = await fetch(`${API_URL}/vehicles/get-parking-detail/${licensePlate}`, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            }
        });

        const detailData = await detailResponse.json();
        
        if (detailData.isParked) {
            alert('This vehicle is already parked!');
            return;
        }

        const response = await fetch(`${API_URL}/parks/userId/${userId}/check-in`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`
            },
            body: JSON.stringify({
                parkingAreaId,
                vehicle: {
                    licensePlate,
                    vehicleType
                }
            })
        });

        if (response.ok) {
            alert('Vehicle parked successfully!');
            event.target.reset();
        } else {
            const error = await response.json();
            alert('Parking failed: ' + error.message);
        }
    } catch (error) {
        alert('An error occurred: ' + error.message);
    }
}

async function handleCheckOut(event) {
    event.preventDefault();
    
    const parkingAreaId = document.getElementById('checkOutParkingArea').value;
    const licensePlate = document.getElementById('checkOutLicensePlate').value;
    const vehicleType = document.getElementById('checkOutVehicleType').value;
    const userId = localStorage.getItem('userId');

    if (!parkingAreaId || !licensePlate || !vehicleType) {
        alert('Please fill in all fields!');
        return;
    }

    try {
        const detailResponse = await fetch(`${API_URL}/vehicles/get-parking-detail/${licensePlate}`, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
                'Accept': 'application/json'
            }
        });

        if (!detailResponse.ok) {
            throw new Error('Failed to get parking details');
        }

        const detailData = await detailResponse.json();
        console.log('Parking details:', detailData);
        
        if (!detailData.isSuccess) {
            throw new Error(detailData.message || 'Failed to get parking details');
        }

        const vehicleData = detailData.response;
        if (!vehicleData.parked) {
            alert('This vehicle is not parked!');
            return;
        }

        const selectedParkingArea = parkingAreas.find(area => area.id === parkingAreaId);
        if (selectedParkingArea.name !== vehicleData.parkingAreaName) {
            alert(`This vehicle is parked in "${vehicleData.parkingAreaName}"! Please select the correct parking area.`);
            return;
        }

        const response = await fetch(`${API_URL}/parks/userId/${userId}/check-out`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${localStorage.getItem('token')}`,
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                parkingAreaId,
                vehicleRequest: {
                    licensePlate,
                    vehicleType
                }
            })
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || 'Check-out failed');
        }

        const data = await response.json();
        if (!data.isSuccess) {
            throw new Error(data.message || 'Check-out failed');
        }

        alert(`Vehicle checked out successfully!\nTotal Cost: ${data.response.totalCost}`);
        event.target.reset();
    } catch (error) {
        console.error('Check-out error:', error);
        alert('Check-out failed: ' + error.message);
    }
}

async function getParkingDetails() {
    const licensePlate = document.getElementById('parkingDetailLicensePlate').value;
    const parkingDetails = document.getElementById('parkingDetails');
    const parkingDetailInfo = document.getElementById('parkingDetailInfo');
    
    if (!licensePlate) {
        parkingDetailInfo.innerHTML = `
            <div class="alert alert-warning">
                Please enter a license plate!
            </div>
        `;
        parkingDetails.style.display = 'block';
        return;
    }

    try {
        parkingDetailInfo.innerHTML = `
            <div class="alert alert-info">
                Loading information...
            </div>
        `;
        parkingDetails.style.display = 'block';

        const token = localStorage.getItem('token');
        if (!token) {
            throw new Error('Session expired');
        }

        const response = await fetch(`${API_URL}/vehicles/get-parking-detail/${licensePlate}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            if (response.status === 401) {
                localStorage.clear();
                window.location.href = 'index.html';
                return;
            }
            throw new Error('Server not responding');
        }

        const data = await response.json();
        
        if (!data.isSuccess) {
            throw new Error(data.message || 'Failed to get data');
        }

        const vehicleData = data.response;
        if (vehicleData.parked) {
            const checkInTime = new Date(vehicleData.checkInTime);
            parkingDetailInfo.innerHTML = `
                <div class="alert alert-success">
                    <strong>Vehicle is Parked</strong><br>
                    Parking Area: ${vehicleData.parkingAreaName || 'Not specified'}<br>
                    Check-in Time: ${checkInTime.toLocaleString('en-US')}<br>
                    Vehicle Type: ${vehicleData.vehicleType || 'Not specified'}
                </div>
            `;
        } else {
            parkingDetailInfo.innerHTML = `
                <div class="alert alert-warning">
                    This vehicle is not currently parked.
                </div>
            `;
        }
    } catch (error) {
        console.error('Error getting parking details:', error);
        parkingDetailInfo.innerHTML = `
            <div class="alert alert-danger">
                <strong>Error!</strong><br>
                ${error.message || 'An error occurred'}
            </div>
        `;
    }
}

function showVehicleSection() {
    document.getElementById('vehicleSection').style.display = 'block';
    document.getElementById('parkingSection').style.display = 'none';
}

function showParkingSection() {
    document.getElementById('vehicleSection').style.display = 'none';
    document.getElementById('parkingSection').style.display = 'block';
}

// Load both parking areas and user vehicles when page loads
window.onload = async function() {
    checkAuth();
    await Promise.all([loadParkingAreas(), loadUserVehicles()]);
}; 