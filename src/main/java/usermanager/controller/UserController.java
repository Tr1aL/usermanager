package usermanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import usermanager.bean.UserFilter;
import usermanager.model.User;
import usermanager.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public ModelAndView register(@ModelAttribute("user") @Valid User user, BindingResult binding) {
        ModelAndView mav = new ModelAndView("register");
        if (binding.hasErrors()) {
            mav.addObject("user", user);
        } else {
            boolean result = userService.registerUser(user);
            if (result) {
                mav.addObject("createdUser", user);
                mav.addObject("user", new User());
            } else {
                mav.addObject("error", "User with this login already exists!");
                mav.addObject("user", user);
            }
        }
        return mav;
    }

    @RequestMapping({"/", "/login"})
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView("login");
        if (error != null) {
            model.addObject("error", "Не верные имя пользователя или пароль!");
        }

        if (logout != null) {
            model.addObject("msg", "Вы успешно вышли из системы!");
        }
        return model;

    }

    @RequestMapping("/users")
    public ModelAndView users() {
        ModelAndView mav = new ModelAndView("users");
        mav.addObject("list", userService.getUserList());
        return mav;
    }

    @RequestMapping("/search")
    public ModelAndView search() {
        return new ModelAndView("search");
    }

    @RequestMapping("/userTable")
    public ModelAndView searchData(@ModelAttribute("filter") UserFilter filter) {
        ModelAndView mav = new ModelAndView("inc/userTable");
        mav.addObject("list", userService.getUserList(filter));
        return mav;
    }
}
