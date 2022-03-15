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
