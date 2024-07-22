import React, { Component } from 'react'
import UserService from '../api/UserService';

class CreateUserComponent extends Component {
    constructor(props) {
        super(props);
        
        this.state = {
            user: {
                email: '',
                password: '',
                firstName: '',
                lastName: '',
                roles: [
                    {
                        role: ''
                    }
                ]
            },
        };
        this.onChangeEmail = this.onChangeEmail.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
        this.onChangeFirstName = this.onChangeFirstName.bind(this);
        this.onChangeLastName = this.onChangeLastName.bind(this)
        this.onChangeRole = this.onChangeRole.bind(this);
        this.saveUser = this.saveUser.bind(this);
}
    onChangeEmail(e) {
        const email = e.target.value;
        this.setState(prevState => ({
            user: {
                ...prevState.user,
                email: email
            }
        }));
}

    onChangePassword(e) {
        const password = e.target.value;
          this.setState(prevState => ({
            user: {
                ...prevState.user,
                password: password
            }
        }));
}

    onChangeFirstName(e) {
        const firstName = e.target.value;
          this.setState(prevState => ({
            user: {
                ...prevState.user,
                firstName: firstName
            }
        }));
}

    onChangeLastName(e) {
        const lastName = e.target.value;
          this.setState(prevState => ({
            user: {
                ...prevState.user,
                lastName: lastName
            }
        }));
    }

    onChangeRole(e) {
        const role = e.target.value;
        const roles = this.state.user.roles;
        roles[0].role = role;
        this.setState(prevState => ({
            user: {
                ...prevState.user,
                roles: [{ role: role}]
            }
        }));
    }

    saveUser(e) {
        e.preventDefault();
        UserService.createUser(this.state.user).then(res => {
            console.log(res.data);
            this.props.history.push('/users');
        })
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add User</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Email:</label>
                                        <input placeholder="Email" name="email" className="form-control"
                                            value={this.state.user.email} onChange={this.onChangeEmail} />
                                    </div>
                                    <div className="form-group">
                                        <label>Password:</label>
                                        <input type="password" placeholder="Password" name="password" className="form-control"
                                            value={this.state.user.password} onChange={this.onChangePassword} />
                                    </div>
                                    <div className="form-group">
                                        <label>First Name:</label>
                                        <input placeholder="First Name" name="firstName" className="form-control"
                                            value={this.state.user.firstName} onChange={this.onChangeFirstName} />
                                    </div>
                                    <div className="form-group">
                                        <label>Last Name:</label>
                                        <input placeholder="Last Name" name="lastName" className="form-control"
                                            value={this.state.user.lastName} onChange={this.onChangeLastName} />
                                    </div>
                                    <div className="form-group">
                                        <label>Role ID:</label>
                                        <input placeholder="Role ID" name="role" className="form-control"
                                            value={this.state.user.roles[0].role} onChange={this.onChangeRole} />
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveUser}>Save</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}


export default CreateUserComponent;