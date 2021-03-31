package com.learn.dp.adapter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/adapter")
public class Controller {

    @RequestMapping("execeute")
    public Object doAction() {
        return "execute action";
    }
}
