package rest.spring.provider_trails.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/test")
  public String testMethod() {
    return "Hi, I'm the test";

  }

}
