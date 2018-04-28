package sa.tws1.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController
{
    @RequestMapping("/login")
    public ModelAndView login(Model model)
    {
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
        {
            return new ModelAndView("home");
        }
        return new ModelAndView("login");
    }

    @RequestMapping("/home")
    public String home()
    {
        return "home";
    }
}
