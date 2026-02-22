import axios from "axios";


async function downloadFile(filePath) {
    await axios.get(`/api/files/${filePath}`, {params: {disposition: 'attachment'}});
}

async function uploadFile(file, path) {
    const formData = new FormData();
    formData.append('file', file); // 'file' 需匹配后端 @RequestParam 名
    const resp = await axios.post('/api/files/upload', formData, {
        headers: {'Content-Type': 'multipart/form-data'},
        params: {path}
    });
    return resp.data;
}

async function uploadFiles(files, path) {
    const formData = new FormData();
    formData.append('files', files); // 'file' 需匹配后端 @RequestParam 名
    const resp = await axios.post('/api/files/upload', formData, {
        headers: {'Content-Type': 'multipart/form-data'},
        params: {path}
    });
    return resp.data;
}

export {downloadFile, uploadFile, uploadFiles}