package com.wildcodeschool.myProjectWithSecurity.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String Welcome() {
        return "Welcome to the SHIELD";
    }

    @GetMapping("/avengers/assemble")
    public String AvengerAssemble() {
        return "Avengers... Assemble";
    }

    @GetMapping("/secret-bases")
    public String secretBases() {
        return "Bordeaux, Bayonne, Toulouse....";
    }

    // Ajout d'un back office
    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin!!!";
    }
}