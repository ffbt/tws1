package sa.tws1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sa.tws1.bean.Employee;
import sa.tws1.service.EmployeeService;

@Controller
public class SetController
{
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/set")
    public String set(Model model)
    {
        Employee employee = employeeService.getEmployee();
        model.addAttribute("employee", employee);
        return "set";
    }
}
