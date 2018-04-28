package sa.tws1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.tws1.bean.Employee;
import sa.tws1.service.EmployeeUtils;

@RestController
public class RegisterController
{
    @RequestMapping("/register")
    public String register(Employee e)
    {
        if (EmployeeUtils.verifyEmployee(e))
        {
            return "{\"state\": \"1\"}";
        }
        else
        {
            return "{\"state\": \"0\"}";
        }
    }
}
