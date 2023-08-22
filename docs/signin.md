# 로그인 과정

AuthController에서 getMapping 으로 로그인을 받도록 한다.

이때 쿼리스트링으로 아이디와 패스워드를 전달받는다.

## 1. 외부의 요청이 SecurityFilterChain을 통과한다.

[SecurityConfig.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fex%2Flab%2Fsecurity%2FSecurityConfig.java)

를 통과하게 되는데, 이때는 jwt가 없으므로 JwtAuthenticationFilter에서 인증을 받지 못한다.

해당 요청은 .requestMatchers에서 permitAll()로 누구의 요청이던지 스프링컨테이너로 들어가게 된다.

## 2. AuthController에서 signIn메소드가 호출된다.

해당 컨트롤러는 memberService의 loginEmailAndPassword 메소드를 호출하게된다.

이는 signUp에서도 사용했던 메소드임을 참고하자.

## 3. Respository를 통해서 email로 member를 찾는다.

email은 유니크함으로 (중복되지 않으므로) 검색의 기준이 될 수 있다.

이메일을 통해서 회원가입된 멤버가 존재하지 않는 경우, 혹은 비밀번호가 일치하지 않는 경우 예외를 발생시킨다.

## 4. Authentication과정을 진행한다.

사용자의 정보를 이용해서 SecurityContextHolder에 인증에 대한 정보를 저장한다.

stateless한 서버이므로, 이번 요청이 처리되는 순간동안만 사용되게 되어진다.

## 5. createJwt를 통해 Jwt를 만들어서 반환한다.

stateless하다 -> 사용자(클라이언트)가 인증에 대한 정보를 관리한다 -> JWT를 이용

jwt 내부에는 유저의 이메일 + 권한이 담겨서 만들어지게 된다.

