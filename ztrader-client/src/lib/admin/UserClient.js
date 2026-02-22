import axios from 'axios'

async function getUserProfile() {
    const resp = await axios.get('/api/admin/user/profile')
    return resp.data
}

async function saveUserProfile(profile) {
    const resp = await axios.post('/api/admin/user/profile', profile)
    return resp.data
}

async function changePassword(passwordChangeRequest) {
    const resp = await axios.post('/api/admin/user/password/change', passwordChangeRequest)
    return resp.data
}

export {getUserProfile, saveUserProfile, changePassword}