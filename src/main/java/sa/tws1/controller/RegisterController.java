package sa.tws1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.tws1.bean.Employee;
import sa.tws1.service.etms.EmployeeService;

@RestController
public class RegisterController
{
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/register")
    public String register(Employee e)
    {
        if (employeeService.verifyEmployee(e))
        {
            return "{\"state\": \"1\"}";
        }
        else
        {
            return "{\"state\": \"0\"}";
        }
    }
}
