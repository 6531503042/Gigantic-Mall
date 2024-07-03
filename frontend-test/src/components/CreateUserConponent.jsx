import React, { Component } from 'react'
import UserService from '../api/UserService';

class CreateUserComponent extends Component {
    constructor(props) {
        super(props);
        
        this.state = {
            user: {
                id: null,
                name: '',
                email: '',
                password: ''
            },
            submitted: false
        }
        this.onChangeName = this.onChangeName.bind(this);
}