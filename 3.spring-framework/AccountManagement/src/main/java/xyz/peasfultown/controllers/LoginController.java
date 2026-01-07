package xyz.peasfultown.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.security.Principal;

@RestController
@RequestMapping("api/v1/login")
@CrossOrigin("*")
public class LoginController {
    @GetMapping
    public ResponseEntity<?> checkLogin(Principal principal) {
        System.out.println("principal: " + principal);
        System.out.println("Username: " + principal.getName());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
