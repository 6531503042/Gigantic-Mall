import axios from "axios";

const USER_API_BASE_URL = "http://localhost:8100/Admin/users";

class UserService {
    
    getUser(){
        return axios.get(USER_API_BASE_URL + '/list');
    }

    createUser(user){
        return axios.post(USER_API_BASE_URL + '/add', user);
    }

    getUserById(userId){
        return axios.get(USER_API_BASE_URL + '/' + userId);
    }

    updateUser(user, userId){
        
    }
} export default new UserService()