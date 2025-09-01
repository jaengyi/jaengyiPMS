import axios from 'axios';

const API_URL = 'http://localhost:8000'; // API Gateway URL

const apiClient = axios.create({
  baseURL: API_URL,
});

// Interceptor to add the JWT token to every request
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('jwt_token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export const login = (email, password) => {
  return apiClient.post('/auth-service/login', { email, password });
};

export const getProjects = () => {
  return apiClient.get('/project-service/projects');
};

export const getProjectDetails = (projectId) => {
  return apiClient.get(`/project-service/projects/${projectId}`);
};

// Add other API calls here...

export const register = (name, email, password) => {
  return apiClient.post('/auth-service/users', { name, email, password });
};
