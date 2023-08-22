# 초등학생도 쉽게 따라할 수 있는 스프링 시큐리티

는 사실 이해가 안되서 직접 만들어본다.

RESTful api server
JWT 사용

좀 더 멋지게 정리해서 스프링 시큐리티를 처음 쓰는 사람도 하루만에 마스터할 수 있게 하고 싶은데
글실력이 부족해서 조금씩 파편화 되어있다.

최신 스프링 시큐리티 권장에 맞추기 위해서 람다식을 사용함.


### 의존성 구성
'org.springframework.boot' version '3.1.2'
'io.jsonwebtoken:jjwt-api:0.11.5'
springframework 6.0.11
spring-security 6.1.2
java corretto-17

### 참고 : 스프링 시큐리티의 흐름
[securitydocs.md](docs%2Fsecuritydocs.md)

### 참고 : 인증에 대하여
[Authentication.md](docs%2FAuthentication.md)

(구버전 문서들)
## 회원가입 + 코드
[signup.md](docs%2Fsignup.md)

## 로그인 + 코드
[signin.md](docs%2Fsignin.md)


# 0단계 : 목표

개인 프로젝트에 스프링 시큐리티를 적용하기 위하여 회원가입 - 로그인 - 권한 인가
세가지 usecase를 기반으로 간단한 서버를 만들면서 그 흐름을 익히고자 한다.

큰 줄기를 잡고 가면 나중에 더 커스텀한 기능이 필요할 때 쉽게쉽게 적용할 수 있으므로,

전체적으로 아키텍쳐가 어떻게 구성되어있고 어떻게 돌아가는 지를 기반으로 진행해나가보고자 한다.

# 1단계 : 기본적인 crud 작성하기

post 도메인과 member 도메인에 대해서 서비스정도까지 간단하게 만들고 시작하자.

목표에서 알 수 있듯이, 기존의 프로젝트에서 시큐리티를 적용하기 위함으로 이와 관련된 설명은 생략하겠다.


# 2단계 : 의존성 추가하기

https://github.com/jwtk/jjwt
implementation 'io.jsonwebtoken:jjwt-api:JJWT_RELEASE_VERSION'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:JJWT_RELEASE_VERSION'
runtimeOnly 'io.jsonwebtoken:jjwt-jackson:JJWT_RELEASE_VERSION'


Restful api server에서 시큐리티를 사용하는 것이 목표다.
기본적인 스프링 시큐리티와 추가적으로 jwt를 이용할 것이기에 해당 의존성을 추가해주자.

# 3단계 : 구현을 통해 알아보는 흐름

기초적인 스프링 시큐리티의 동작을 위해서 구현해야하는 것들은 다음과 같다.

### JwtProvider

AuthenticationProvider 역할을 하는 구현체이다.

Jwt과 관련된 모든 책임과 역할을 진행한다.

### JwtAuthenticationFilter

AuthenticationFilter 역할을 한다.
스프링 시큐리티 필터에 등록되어서, 요청에 포함된 Jwt에 대해 JwtProvider를 이용해서 요청을 '필터'한다.

### CustomUserDetailService

UserDetailsService 역할을 한다.

DAO이며 시큐리티에서 회원에 대해 조회할 때 사용되어진다. (아마도)

### SecurityConfig

실질적인 필터 구성에 대해서 설정하고, 이를 빈으로 등록해준다.

여기서 생성된 빈은 스프링 시큐리티의 프록시객체가 호출해서 사용되어진다.

### Member implements UserDetail

멤버엔티티에서 UserDetail 인터페이스을 구현해야한다.

해당 인터페이스 또한 시큐리티에서 회원에 대한 정보를 조회하고 검증할 때 사용하게 된다.

### 그 외

회원가입과 로그인 등에 사용될 컨트롤러와 서비스를 구현해야한다.

개인 혹은 단체의 철학에 따라 어떤 곳에 어떤 식으로 배치할 지는 각자 다르므로...

# 4단계 : 구현으로 알아보기

## JwtProvider

[JwtProvider.java](src%2Fmain%2Fjava%2Fcom%2Fex%2Flab%2Fsecurity%2FJwtProvider.java)

시크릿 키부분 수정 필요

동작 테스트 코드 작성 필요

권한 role에 대해서 설정 필요

post 5회 작성 시 role 내려가는 기능 구현 필요




---

### 참조링크

https://docs.spring.io/spring-security/reference/servlet/architecture.html

https://github.com/spring-projects/spring-security-samples/tree/main

https://kim-jong-hyun.tistory.com/33

한글사이트 중 제일 많이 봄 : https://chanho0912.tistory.com/44
감사합니다 선생님!

https://github.com/choiwoonsik/springboot_RestApi_App_Project/tree/main/restApiSpringBootApp/src/main/java/com/restApi/restApiSpringBootApp/domain

https://github.com/spring-projects/spring-security-samples/tree/main/servlet/spring-boot/java/jwt/login

https://velog.io/@nyong_i/Jwt%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0

https://velog.io/@coconenne/Spring-Security-%ED%95%B5%EC%8B%AC%EC%A0%95%EB%A6%AC

https://github.com/jwtk/jjwt#jwt-compression

https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.htmlhttps://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html

https://dev-coco.tistory.com/123

https://jaimemin.tistory.com/1874

https://gksdudrb922.tistory.com/217