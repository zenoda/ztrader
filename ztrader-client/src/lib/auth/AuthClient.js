import axios from 'axios'

async function login(credential) {
    const resp = await axios.post('/api/auth/login', credential)
    return resp.data
}

async function logout() {
    await axios.post('/api/auth/logout')
}

async function checkLoginStatus() {
    try {
        const response = await axios.get('/api/auth/status');
        return response.data.loggedIn;
    } catch (error) {
        // 处理错误，例如网络问题或后端返回的错误
        console.error('Error checking login status:', error);
        return false; // 在出现错误时默认认为未登录
    }
}

export {login, logout, checkLoginStatus}