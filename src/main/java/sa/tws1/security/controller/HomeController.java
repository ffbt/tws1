package sa.tws1.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sa.tws1.service.EmployeeService;

@Controller
public class HomeController
{
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = {"/", "/login"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(Model model)
    {
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
        {
            employeeService.setEmployee();
            return new ModelAndView("home");
        }
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home()
    {
        return "home";
    }
}
