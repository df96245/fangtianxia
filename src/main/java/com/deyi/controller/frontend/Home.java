package com.deyi.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by df96245
 */

@Controller
public class Home {

    @RequestMapping(value = {"home","/"},method=RequestMethod.GET)
    public String home(){
        return "home";
    }
}
