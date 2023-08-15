# 초등학생도 쉽게 따라할 수 있는 스프링 시큐리티

는 사실 이해가 안되서 직접 만들어본다.

RESTful api server
JWT 사용

post에 대한 crud + 등급에 따른 세부 권한 설정

일정 갯수 이상의 post를 사용하면 등급이 내려가는 기능 목표


# 1단계 : 기본적인 crud 작성하기

post 와 member를 만들었다.

# 2단계 : 의존성 추가하기

https://github.com/jwtk/jjwt
implementation 'io.jsonwebtoken:jjwt-api:JJWT_RELEASE_VERSION'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:JJWT_RELEASE_VERSION'
runtimeOnly 'io.jsonwebtoken:jjwt-jackson:JJWT_RELEASE_VERSION'


# 3단계 : JwtToken 발급해줄 Bean 만들기





### 참조링크

https://docs.spring.io/spring-security/reference/servlet/architecture.html

https://github.com/spring-projects/spring-security-samples/tree/main

https://kim-jong-hyun.tistory.com/33

https://chanho0912.tistory.com/44

https://github.com/choiwoonsik/springboot_RestApi_App_Project/tree/main/restApiSpringBootApp/src/main/java/com/restApi/restApiSpringBootApp/domain

https://github.com/spring-projects/spring-security-samples/tree/main/servlet/spring-boot/java/jwt/login

