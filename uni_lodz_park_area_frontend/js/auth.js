const API_URL = 'http://localhost:1222/api/v1';

function toggleForms() {
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    
    if (loginForm.style.display === 'none') {
        loginForm.style.display = 'block';
        registerForm.style.display = 'none';
    } else {
        loginForm.style.display = 'none';
        registerForm.style.display = 'block';
    }
}

async function handleLogin(event) {
    event.preventDefault();
    
    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;

    try {
        const response = await fetch(`${API_URL}/auth/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        const data = await response.json();
        
        if (response.ok && data.isSuccess) {
            const jwtResponse = data.response;
            localStorage.setItem('token', jwtResponse.token);
            
            // Role check
            const role = jwtResponse.roles && jwtResponse.roles.length > 0 ? jwtResponse.roles[0] : null;
            if (!role) {
                throw new Error('User role not found');
            }
            
            localStorage.setItem('role', role);
            localStorage.setItem('userId', jwtResponse.id);
            
            if (role === 'ROLE_ADMIN') {
                window.location.href = 'admin.html';
            } else if (role === 'ROLE_DRIVER') {
                window.location.href = 'driver.html';
            } else {
                throw new Error('Invalid user role');
            }
        } else {
            throw new Error(data.message || 'Login failed');
        }
    } catch (error) {
        alert('Login failed: ' + error.message);
        console.error('Login error:', error);
    }
}

async function handleRegister(event) {
    event.preventDefault();
    
    const fullName = document.getElementById('registerFullName').value;
    const username = document.getElementById('registerUsername').value;
    const email = document.getElementById('registerEmail').value;
    const password = document.getElementById('registerPassword').value;
    const role = document.getElementById('registerRole').value;

    try {
        const response = await fetch(`${API_URL}/auth/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                fullName,
                username,
                email,
                password,
                role
            })
        });

        const data = await response.json();
        
        if (response.ok && data.isSuccess) {
            alert('Registration successful! Please login.');
            toggleForms();
        } else {
            throw new Error(data.message || 'Registration failed');
        }
    } catch (error) {
        alert('Registration failed: ' + error.message);
        console.error('Register error:', error);
    }
}

function checkAuth() {
    const token = localStorage.getItem('token');
    const role = localStorage.getItem('role');
    
    if (!token || !role) {
        window.location.href = 'index.html';
        return;
    }

    // Page control
    const currentPage = window.location.pathname.split('/').pop();
    if (role === 'ROLE_ADMIN' && currentPage !== 'admin.html') {
        window.location.href = 'admin.html';
    } else if (role === 'ROLE_DRIVER' && currentPage !== 'driver.html') {
        window.location.href = 'driver.html';
    }
}

function logout() {
    const token = localStorage.getItem('token');
    
    if (token) {
        fetch(`${API_URL}/auth/logout`, {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }).catch(console.error);
    }
    
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('userId');
    window.location.href = 'index.html';
} 