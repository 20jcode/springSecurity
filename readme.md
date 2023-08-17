# 초등학생도 쉽게 따라할 수 있는 스프링 시큐리티

는 사실 이해가 안되서 직접 만들어본다.

RESTful api server
JWT 사용

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


# 3단계 : 회원가입 과정

서버에 누군가가 회원가입을 위해 http Post request를 보낸다고 생각하고 시작하겠다.

위의 스프링시큐리티 기본 흐름에서 보면 알다싶이, 스프링은 서블릿 컨테이너에서 특정 서블릿(예를 들어, 스프링 컨테이너)

으로 가기 전에 필터로 동작한다.

request 데이터를 중심으로 생각해보면, 몇몇의 과정을 거쳐서 스프링 시큐리티의 필터로 들어오게 된다.

스프링 시큐리티는 위임 프록시(DelegatingFilterProxy)로 필터를 제공하고 있으며, 실제 보안과 관련된 동작은 스프링 컨테이너의 Bean에 위임하고 있다.

1. request요청이 서블릿 컨테이너로 들어오게됨 (스프링 부트 기준 Tomcat)

2. 서블릿으로 가기 전 필터를 거치게 됨 - 스프링 시큐리티 필터(DelegatingFilterProxy)를 만남.

3. DelegatingFilterProxy에서 스프링 컨테이너에서 SecurityFilterChain 빈을 얻은 다음 request요청의 처리를 위임함

4. 이떄 스프링 컨테이너의 SecurityFilterChain 빈은 SecurityConfig.class(@EnableWebSecurity - @Configration이 포함되어있음)
   에서 @Bean에서 반환된 객체이다.

5. request요청이 처리되어 SecurityFilterChain객체을 통과하면 실제 목적지인 스프링 컨테이너의 Controller로 이동하게됨.

대략적인 흐름은 이러하다.

이제는 실제 코드를 기준으로 어떤 식으로 동작하는 지 보겠다.

---

## 회원가입 + 코드
[signup.md](docs%2Fsignup.md)










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