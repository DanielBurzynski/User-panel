package pl.spring.springtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.spring.springtest.component.mailer.Message;
import pl.spring.springtest.repository.UserRepository;
import pl.spring.springtest.service.SignUpService;
import pl.spring.springtest.service.impl.JpaUserDetailsService;

@Controller
public class WebController {

    private SignUpService signUpService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    JpaUserDetailsService jpaUserDetailsService;


    @Autowired
    public WebController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @RequestMapping(value = "/user_panel", method = RequestMethod.GET)
    public ModelAndView userPanel(ModelAndView mav) {
        mav.setViewName("user_panel");

        return mav;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index");

        return mav;
    }

    @GetMapping("/about_me")
    public String aboutMe(Model model) {
        model.addAttribute("about_me", model);

        return "about_me";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("home", model);

        return "redirect:index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView mav) {
        mav.setViewName("login");

        return mav;
    }

    @RequestMapping(value = "/admin_panel", method = RequestMethod.GET)
    public ModelAndView adminPanel(ModelAndView mav) {
        mav.setViewName("admin_panel");

        return mav;
    }


    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }

}
