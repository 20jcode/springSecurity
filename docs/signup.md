# 회원가입 과정에서

## 회원가입 요청의 처리

객체지향적인 관점에서, 회원가입과 관련된 인증에 대한 처리는 하나의 도메인이 관리하는 것이 좋다고 생각되어진다.

패키지를 하나 만들자.

[auth](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fex%2Flab%2Fauth)

## 회원가입 요청을 받을 컨트롤러 구상

[AuthController.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fex%2Flab%2Fauth%2Fcontroller%2FAuthController.java)

해당 컨트롤러는 @PostMapping 을 통해서 회원가입과 연관된 요청을 받는다.

이 때 @RestController 을 통해서 응답코드등을 처리하도록 하도록 하겠다.

회원가입에서는 request body로 SingUpForm dto를 받도록 하고, response로는 SignUpResponse를 responsebody로 반환하도록 하겠다.

```
@PostMapping("/auth/signup")
public SignUpResponse signUp(@Valid @RequestBody SignUpForm signUpForm,
                             BindingResult bindingResult){
}
```

## 회원가입을 처리할 서비스 구상

컨트롤러의 구현은 나중에 하도록 하고, 해당 컨트롤러에서 signUpForm을 받아서 처리할 서비스를 만들도록 하겠다.

해당 서비스는 member도메인과 밀접한 연관이 있다.

현재는 스프링 시큐리티를 익히는 목적이므로, 일단 memberService에서 서비스를 처리해 줄 수 있도록 하겠다.

[MemberService.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fex%2Flab%2Fmember%2Fservice%2FMemberService.java)

```
@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	
	@Transactional
	public SignUpResponse createNewMember(SignUpForm signUpForm){

		return null;
	}
}
```

그럼 이제 세부적인 구현을 통해 회원가입 과정을 알아보자.

## 1. 외부의 요청이 컨트롤러로 들어옴

회원가입을 담당하는 컨트롤러를 통해서 회원가입 요청이 들어오게 된다.

이때, 프론트 클라이언트에서는 회원가입 요청을 받는 "/auth/signup"의 URL로 요청이 들어오게 된다.

[AuthController.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fex%2Flab%2Fauth%2Fcontroller%2FAuthController.java)

## 2. 스프링 시큐리티 필터를 지나감

하지만, 스프링 컨테이너의 컨트롤러객체가 외부의 요청을 받기 전에 스프링 시큐리티가 제공하는

서블릿 필터가 해당 요청을 가로채게 된다.

또한 이때문에 회원가입과 로그인에 대해서는 AuthController로 분리해두었다.

스프링 시큐리티 필터에서는 필터를 통과하며 외부의 http 요청에 대한 변경을 진행하는데

이때 인증과 권한 인가에 대한 과정을 거치게 된다.

[SecurityConfig.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fex%2Flab%2Fsecurity%2FSecurityConfig.java)

filterChain메소드를 보면, http 객체에 대해서

.csrf().formLogin() 등등 요청에 대한 변경을 하는 객체를 .bulid() 만들어두고 이를 return 한다.

따라서 반환된 HttpSecurity 객체 인스턴스가 빈으로 되어서 스프링시큐리티 필터에서 프록시객체에서 실제

인증과 권한 인가를 거치게 해준다.

AuthController는 여기서 member 도메인에 대한 작업 중, 권한이 없는 작업만을 담당해주기 위해서

분리해둔 것이다.

## 3. 허용된 도메인으로 필터를 통과함

권한이 없어도 통과될 수 있으므로 필터를 통과해서 스프링 컨테이너에 요청이 전달되어진다.

## 4. auth 패키지의 AuthController로 들어감

스프링컨테이너는 요청을 처리할 컨트롤러에게 넘겨주게 된다.

signUp메소드에서 해당 요청을 처리하기로 했다.

@RequestBody로 http 요청의 Body부분을 SignUpForm으로 받는다. (이는 스프링 시큐리티에서 처리해준다.)

## 5. MemberService를 이용해 validation 진행 - emailIsPresent 메소드

validation과정(이메일 중복확인만 하였지만, 실제로는 더 많은 validation이 필요할 것이다.)을 거친 다음

memberService에게 새로운 멤버 생성 요청을 전달한다.

## 6. MemberService를 이용해 회원가입 절차를 진행 - createNewMember 메소드



## 7. createNewMember 메소드 내부에서

### memberRepository를 통해서 엔티티 save
이때 passwordEncoder를 이용해서 저장

### private method 인 loginEmailAndPassword 를 호출

### 해당 메소드에서 jwt생성 진행

### jwtPorvider를 이용해서 jwt 생성

