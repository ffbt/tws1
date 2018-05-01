package sa.tws1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sa.tws1.bean.Employee;
import sa.tws1.service.etms.EmployeeService;

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

    @RequestMapping("/updateMessage")
    @ResponseBody
    public String updateMessage(Employee employee)
    {
        employeeService.updateMessage(employee);
        return "{\"state\": \"1\"}";
    }

    @RequestMapping(value = "/modifyPassword")
    @ResponseBody
    public String modifyPassword(@RequestParam(value = "old_password") String oldPassword,
                                 @RequestParam(value = "new_password") String newPassword)
    {
        if (employeeService.verifyPassword(oldPassword, newPassword))
            return "{\"state\": \"1\"}";
        return "{\"state\": \"0\"}";
    }
}
