package com.wildcodeschool.myProjectWithSecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .anonymous()
                .and()
                // un bloc
                .authorizeRequests()
                    //.anyRequest().authenticated() // toutes les requêtes doivent être authentifiées
                    .antMatchers("/avengers/assemble")
                    .hasRole("CHAMPION")
                    .antMatchers("/secret-bases")
                    .hasRole("DIRECTOR")
                    .antMatchers("/").permitAll()
                    .and()
                // un bloc
                // l'authentification passe par un formulaire de connexion
                .formLogin()

                    .and()
                // un bloc
                .httpBasic(); // l'authentification basique HTTP.
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* // Cette déclaration permet de déclarer des utilisateurs "en mémoire" -- c'est-à-dire sans base de données.
           // Avec ce code, tu es en train d'associer l'identifiant user au mot de passe password.
           // Tu peux relancer ton application, et aller au formulaire de login... mais password ne marchera pas !
           // Avec Spring 4, il était possible de stocker des mots de passe en clair.
           auth.inMemoryAuthentication().withUser("user").password("password").roles("");
        */
        // Avec Spring 5, les choses sont un peu plus subtiles : un décodeur par défaut est utilisé.
        // Note que tu es obligé d'associer des rôles à ton utilisateur : pour l'instant, une chaîne de caractères vide fera l'affaire.
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("Nick")
                    .password(encoder.encode("flerken"))
                    .roles("DIRECTOR")
                    .and()
                .withUser("Steve")
                    .password(encoder.encode("motdepasse"))
                    .roles("CHAMPION")
                    .and()
                .withUser("admin")
                    .password(encoder.encode("12345678"))
                    .roles("ADMIN");
    }

}

