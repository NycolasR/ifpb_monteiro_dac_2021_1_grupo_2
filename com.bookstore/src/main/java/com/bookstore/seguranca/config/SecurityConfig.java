package com.bookstore.seguranca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bookstore.service.AutenticacaoService;

/**
 * @author NPG
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
//	/** Para facilitar a geração de senha encriptada */
//	public static void main(String[] args) {
//		System.out.println("Senha 1: " + new BCryptPasswordEncoder().encode("senha1"));
//		System.out.println("Senha 2: " + new BCryptPasswordEncoder().encode("senha2"));
//		System.out.println("Senha 3: " + new BCryptPasswordEncoder().encode("senha3"));
//		System.out.println("Senha 4: " + new BCryptPasswordEncoder().encode("senha4"));
//		System.out.println("Senha 5: " + new BCryptPasswordEncoder().encode("senha5"));
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/resources/**", "/webjars/**").permitAll()
			.antMatchers(HttpMethod.GET, "/perfil/{id}").hasAuthority("CLIENT")
			.antMatchers(HttpMethod.GET, "/endereco_form/{id}").hasAuthority("CLIENT")
			.antMatchers(HttpMethod.GET, "/administracao/*").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/autor").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/autorform/{id}").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/editora").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/editoraform/{id}").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/formapagamento").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/livro").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/livroform/{id}").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/inicio").permitAll()
			.antMatchers(HttpMethod.GET, "/cadastre-se").permitAll()
			.anyRequest().permitAll()
			.and()
			
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/inicio", true)
//			.and()
//			.rememberMe()
			
			.and()
			.csrf().disable()
			.logout().logoutSuccessUrl("/inicio");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
}




