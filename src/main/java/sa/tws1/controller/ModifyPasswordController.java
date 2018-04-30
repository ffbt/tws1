package sa.tws1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sa.tws1.service.EmployeeService;

@RestController
public class ModifyPasswordController
{
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/modifypassword")
    public String modifyPassword(@RequestParam(value = "old_password") String oldPassword,
                                 @RequestParam(value = "new_password") String newPassword)
    {
        if (employeeService.verifyPassword(oldPassword, newPassword))
            return "{\"state\": \"1\"}";
        return "{\"state\": \"0\"}";
    }
}
