package sa.tws1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.tws1.bean.Employee;
import sa.tws1.service.EmployeeService;

@RestController
public class UpdateMessageController
{
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/updatemessage")
    public String updateMessage(Employee employee)
    {
        employeeService.updateMessage(employee);
        return "{\"state\": \"1\"}";
    }
}
