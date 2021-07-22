package org.id2k1149.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CalculatorController {

    @GetMapping("/calculator")
    public String myCalculator(@RequestParam("a") Integer a,
                            @RequestParam("b") Integer b,
                            @RequestParam("action") String action,
                            Model model) {
        double result;
        switch(action) {
            case "*":
                result = a * b;
                break;
            case "add":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "/":
                result = a / (double)b;
                break;
            default:
                result = 0;
                break;
        }
        model.addAttribute("result", result);

        return "first/calculator";

    }
}
