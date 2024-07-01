
# Gigantic-Management API Reference

![Gigantic Logo](./assets/Gigantic_icon.png)

## User Endpoints

### Get All Users

```http
  GET /Admin/users/list
```


#### Get all items

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |
| `email` | `string` | **Optional** Filter by email |
| `role` | `string` | **Optional** Filter by role |
| `sortField` | `string` | **Optional**. Field to sort by |
| `sortDirection` | `string` | **Optional**. Sort direction (asc or desc) |

#### Add User

```http
  POST /Admin/users/add
```

```json
{
    "email": "example@domain.com",
    "password": "password123",
    "firstName": "FirstName",
    "lastName": "LastName",
    "roles": [
        {
            "role": 1
        }
    ]
}

```

#### Enable/Disable User

```http
  PUT /Admin/users/{id}/enabled/{status}
```

```json
{
    "email": "example@domain.com",
    "password": "newpassword",
    "firstName": "FirstName",
    "lastName": "LastName",
    "roles": [
        {
            "id": 1
        }
    ]
}


```

####Example JSON Body with Multiple Roles

```json
{
    "email": "example@domain.com",
    "password": "newpassword",
    "firstName": "FirstName",
    "lastName": "LastName",
    "roles": [
        {
            "id": 1,
            "name": "Admin"
        },
        {
            "id": 2,
            "name": "Sales"
        }
    ]
}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. Id of user to edit |



#### Delete User by ID

```http
  DELETE /Admin/users/deleteById/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. Id of user to delete |

#### Delete User by Email

```http
  DELETE /Admin/users/deleteById/{email}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**. Email of user to delete |



#### Exporter with CSV

```http
  GET /exporter/csv
```

Example result (CSV format)
```bash
id,email,firstName,lastName,enabled,roles
1,example@domain.com,FirstName,LastName,true,Admin
2,another@example.com,AnotherFirstName,AnotherLastName,false,Sales

```

#### Exporter with Excel

```http
  GET /exporter/excel
```
| id | email     | firstName | lastName | enabled | roles |
| :-------- | :------- | :------- | :------- | :------- | :------- |
| `1` | `example@domain.com` |  `FirstName` | `LastName` | `true` | `Admin` |
| `1` | `another@example.com` |  `AnotherFirstName` | `AnotherLastName` | `false` | `Sales` |

#### Exporter with PDF

```http
  GET /exporter/pdf
```

Example result (PDF format):
```bash
-------------------------------------------------
| ID | Email               | First Name | Last Name | Enabled | Roles  |
-------------------------------------------------
| 1  | example@domain.com  | FirstName  | LastName  | true    | Admin  |
-------------------------------------------------
| 2  | another@example.com | AnotherFirstName | AnotherLastName | false | Sales |
-------------------------------------------------

```

