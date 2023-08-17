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

그럼 이제 세부적인 구현을 해보겠다.

## 구현 1 : signUpForm에서 필요한 것

dto를 통해서 먼저 어떤 정보가 필요한지 생각해보겠다.


