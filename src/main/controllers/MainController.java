package main.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by admin on 15.05.2017.
 */
@Controller
public class MainController {

    @Controller
    @RequestMapping("/welcome")
    public class HelloController {
        @RequestMapping(method = RequestMethod.GET)
        public String printWelcome(ModelMap model) {
            model.addAttribute("value", "Hello,user");
            return "welcome";

        }
    }

    @Controller
    @RequestMapping("/only_for_admin")
    public class adminController {
        @RequestMapping(method = RequestMethod.GET)
        public String printWelcome(ModelMap model) {
            model.addAttribute("value", "Hello,admin");
            return "only_for_admin";

        }
    }

    @Controller
    @RequestMapping("/only_for_user")
    public class userController {
        @RequestMapping(method = RequestMethod.GET)
        public String printWelcome(ModelMap model) {
            model.addAttribute("value", "Hello,user");
            return "only_for_user";

        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }




}
