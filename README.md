# Rentopia



### 2022/02/19
1. upgrade h2 from 1.4.197 -> 2.1.210
2. add "spring.jpa.properties.hibernate.globally_quoted_identifiers=true" in application.properties to fix the broken upgrade of h2

### 2022/02/28
1. add lombok dependency and plugin config
2. modify User and UserController with lombok annotations

### 2022/03/12
1. add spotify/docker-maven-plugin in pom.xml
2. add finalName in pom.xml
3. add Dockerfile in root
4. modify github actions config file, indicate the "package" action to "package docker:build"

### 2022/03/15
1. open a dockerhub repo
2. tag and push image to dockerhub

### 2022/3/20
1. add firebase sdk
2. connect to firebase with credential success
3. decode idToken from authToken success
4. [next] use authToken as a bearer token to protect api endpoints


### 2022/3/24
1. when running github action workflow, set firebase credential as a env variable
2. read firebase credential from file(local) and env(build/runtime)

### 2022/4/5
1. add firestore config 
2. fetch data(Asset and FirestoreController) from firestore

### 2022/4/13
1. add firestore CRUD funtions and apis

### 2022/4/14
1. make firestore CRUD functions generic 
