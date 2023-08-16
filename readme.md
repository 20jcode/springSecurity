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

# 3단계 : SecurityConfig 작성해주기

참고 : 스프링 시큐리티의 흐름에 대해서
[securitydocs.md](docs%2Fsecuritydocs.md)

이제 인증이 어떤 식으로 돌아가는 지 알아볼 차래이다.

인증(Authentication)은 사용자A라고 주장하는 자가 진짜 사용자A인지 확인하는 과정이다.




# 4단계 : Jwt 발급해줄 Bean 만들기

1. 먼저 Member 엔티티에 대한 설정을 잡아준다.

이메일을 기준으로 아이디를 분류할 것이기에 unique 설정을 해준다. (중복방지)

2. 시큐리티는 하나의 도메인으로 분류할 수 있다고 생각하기에, 시큐리티에 관한 패키지를 만들어준다.

JwtProvider클래스를 만든다.

스프링 컨테이너에 등록되어서 빈으로 동작할 것이며, Jwt에 대한 책임을 지게 된다.

jwtProvider빈은 스프링 시큐리티의 GenericFilter를 상속받아서 만들어지는 커스텀필터에서

주입되어서 사용되어진다.

# 막간 상식 : 그래서 이거 왜 씀?

## ex) 회원가입 과정에서

signUp과 관련된 서비스가 호출되어지면, validation(이메일이 중복되는지, 비밀번호를 제대로 쳤는지 등등)과정을

거친다음, member entity에 등록되어진다. 이때는 jwt가 사용되지 않는다.

회원가입은 모두에게 열려있는 서비스이다.(일반적으로)


## ex2) 로그인과 그 이후에서

이떄 jwt가 사용되어진다.

사용자는 한번 로그인하고 서버에 작업을 진행할 때마다 로그인하고 싶지않을 것이다.

서버는 사용자의 요청이 해당 사용자가 맞는지, 권한이 있는지 등에 대해서 알아야한다.

즉, Authentication : 인증 -> 로그인 등을 통해서 사용자가 자신임을 증명하는 과정

Authorization : 권한 부여 -> 인증된 사용자가 특정 서비스나 리소스에 접근할 수 있게 하는 것

이때, Authentication과 Authorization에서 한번의 인증이후 이를 이어나가기 위해 JWT가 주로 사용되어진다.










### 참조링크

https://docs.spring.io/spring-security/reference/servlet/architecture.html

https://github.com/spring-projects/spring-security-samples/tree/main

https://kim-jong-hyun.tistory.com/33

https://chanho0912.tistory.com/44

https://github.com/choiwoonsik/springboot_RestApi_App_Project/tree/main/restApiSpringBootApp/src/main/java/com/restApi/restApiSpringBootApp/domain

https://github.com/spring-projects/spring-security-samples/tree/main/servlet/spring-boot/java/jwt/login

https://velog.io/@nyong_i/Jwt%EB%A1%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0

https://velog.io/@coconenne/Spring-Security-%ED%95%B5%EC%8B%AC%EC%A0%95%EB%A6%AC