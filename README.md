## What
User Api includes CRUD

## How to start

### local
```
docker-compose up
```
The server will be running at http://localhost:8080

## APIS
1. save
```
url: /users
methdo: POST
body: { name: "test", age: 18 }
```

2. delete
```
ur: /users/:userId
method: DELETE
```

3. update
```
url: /users/:userId
method: PUT
body: { name: "test1", age: 19 }
```

4. get
```
url: /users/:userId
method: GET
```

5. search by age
```
url: /users/age-range
method: GET
queryParams: { upper: 10, lower: 18 }
```

6. search by name
```
url: /users/name-pattern
method: GET
queryParams: { name: "te" }
```