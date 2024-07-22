import React, { Component} from "react";
import UserService from "../api/UserService";

class ListUserComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: []
        }
        this.addUser = this.addUser.bind(this);
        this.editUser = this.editUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);
    }

    deleteUser(id) {
        UserService.deleteUser(id).then(response => {
            this.setState({users: this.state.users.filter(user => user.id !== id)});
        });
    }

    viewUser() {
        this.props.history.push(`/list/`);
    }

    componentDidMount() {
        UserService.getUsers().then(response => {
            this.setState({users: response.data})
        }).catch(error => {
            console.log(error);
        });
    }
}