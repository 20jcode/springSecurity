# 권한 인가에 대해서

이제 스프링 시큐리티의 SecurityConfig에서 권한 인가과정을 구현한다.

[SecurityConfig.java](..%2Fsrc%2Fmain%2Fjava%2Fcom%2Fex%2Flab%2Fsecurity%2FSecurityConfig.java)

```
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{...}
```

스프링 프레임워크의 Config파일과 같은 역할이다.

해당 클래스 내부에서 @Bean으로 설정된 메소드의 리턴된 객체를 빈으로 등록해주고,

delegate 시 스프링 컨테이너 내부의 해당 빈을 찾아서 검증절차를 진행하게 된다.

```
@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.formLogin(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.httpBasic(AbstractHttpConfigurer::disable)
				.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Restful 하기 위한 설정들
				.authorizeHttpRequests(authorRequest->authorRequest
						.requestMatchers("/auth/signin","/test/signup").permitAll()
						.anyRequest().hasRole("LOW"))
				.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
		.build();
		
		
	}
```
https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter/

해당 부분을 참고해서 설정을 해준다.



SecurityConfig에서 먼저 filterChain을 만들어줌

addFilterBefore를 통해서 커스텀 jwt 필터를 추가해줌

Restful을 위해서 stateless 한 서버이기에 기본적인 설정을 꺼줌

addFilterBefore에서 UsernamePasswordAuthenticationFilter.class 는 스프링 시큐리티에서 기본적으로 제공하는 필터로

formLogin에서 호출됨

disable했지만 그 앞에 필터가 삽입되어짐.


## 

필터를 추가해줌. - JwtAuthenticationFilter

security 패키지에 넣어주고자함

GenericFilterBean을 확장한 것

