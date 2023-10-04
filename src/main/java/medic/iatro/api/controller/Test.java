package medic.iatro.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Test {
    @GetMapping
    public String hello(){
        return "tets mapping level ler";
    }
}
