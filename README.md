# Rentopia



### 2022/02/19
1. upgrade h2 from 1.4.197 -> 2.1.210
2. add "spring.jpa.properties.hibernate.globally_quoted_identifiers=true" in application.properties to fix the broken upgrade of h2

### 2022/02/28
1. add lombok dependency and plugin config
2. modify User and UserController with lombok annotations