import axios from 'axios';

const API_BASE_URL = `http://localhost:8080/api/ecolight`;

const getTriangleCount = (params) => {
    return axios.post(API_BASE_URL + '/triangle-calculator', params);
}

export default {
    getTriangleCount
};