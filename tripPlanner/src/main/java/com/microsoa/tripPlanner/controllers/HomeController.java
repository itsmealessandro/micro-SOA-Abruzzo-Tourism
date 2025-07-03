package com.microsoa.tripPlanner.controllers;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

  @GetMapping("/home")
  public String showHome() {
    return "homepage"; // this will search in resources/templates

  }

  /*
   * This method is expecting two parameters with name "location" and "date"
   * Model is a special Spring Object responsible of the comunication between
   * controller and view
   */
  @PostMapping("/request")
  public String tripRequest(@RequestParam String location, @RequestParam LocalDate date, Model model) {

    System.out.println("requeste received with data: " + location + " " + date);

    // This is a sample
    String result;
    if (location.equals("a")) {

      result = "OK";
    } else {
      result = "NOT OK";
    }

    model.addAttribute("message", result);

    // TODO: here should be called the specific service
    // something like this is expected:

    // tripPlannerService.tripRequest(location,date)
    // a List of results should be then returned to the client
    return "homepage";
  }

}
