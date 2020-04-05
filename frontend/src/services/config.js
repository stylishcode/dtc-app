import axios from 'axios'

export const http = axios.create({
    // baseURL: 'http://192.168.0.6:8083/api/'
    baseURL: 'http://localhost:8083/api/'
})