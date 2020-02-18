package br.com.unip.cadastro.security

import br.com.unip.cardapio.security.JWTAuthenticationFilter
import br.com.unip.cadastro.security.filter.CorsFilterCustom
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers("/v1/pessoa-fisica/**", "/v1/pessoa-juridica/**", "/v1/cadastros/**")
    }

    @Throws(Exception::class)
    public override fun configure(http: HttpSecurity) {
        http.cors()
                .and()
                .csrf().disable()

        http.csrf().disable().authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(CorsFilterCustom(), UsernamePasswordAuthenticationFilter::class.java)

                .addFilterBefore(JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}