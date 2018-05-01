package sa.tws1.service.etms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sa.tws1.bean.Employee;
import sa.tws1.bean.Role;
import sa.tws1.dao.CompanyDAO;
import sa.tws1.dao.EmployeeDAO;
import sa.tws1.dao.RoleDAO;
import sa.tws1.util.CommonUtil;
import sa.tws1.util.MD5Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private RoleDAO roleDAO;

    private static final int ROLE_START = 0, ROLE_END = 1;
    private static final int COMPANY_START = 1, COMPANY_END = 2;
    private static final int DEPARTMENT_START = 2, DEPARTMENT_END = 3;

    public boolean verifyEmployee(Employee e)
    {
        String id = e.getId();
        if (id.length() != 8)
            return false;
        try
        {
            List<Role> list = new ArrayList<>();
            int i = Integer.parseInt(id.substring(ROLE_START, ROLE_END));
            int company = Integer.parseInt(id.substring(COMPANY_START, COMPANY_END));
            int department = Integer.parseInt(id.substring(DEPARTMENT_START, DEPARTMENT_END));
            e.setPassword(MD5Util.encode(e.getPassword()));
            e.setCompany(companyDAO.findById(company).get());
            e.setDepartment(CommonUtil.departments[department]);
            add(0, e, list);
            add(i, e, list);
            if (i == 2)
                add(3, e, list);
            roleDAO.saveAll(list);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    private void add(int id, Employee e, List<Role> list)
    {
        Role role = roleDAO.findById(id).get();
        if (role.getEmployees() == null)
            role.setEmployees(new HashSet<>());
        role.getEmployees().add(e);
        list.add(role);
    }

    public boolean verifyPassword(String oldPassword, String newPassword)
    {
        Employee employee = getEmployee();
        if (employee.getPassword().equals(MD5Util.encode(oldPassword)))
        {
            employee.setPassword(MD5Util.encode(newPassword));
            employeeDAO.save(employee);
            return true;
        }
        return false;
    }

    public Employee getEmployee()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return employeeDAO.findById(userDetails.getUsername()).get();
    }

    public void updateMessage(Employee e)
    {
        Employee employee = getEmployee();
        employee.setUserName(e.getUserName());
        employee.setEmail(e.getEmail());
        employee.setPhoneNumber(e.getPhoneNumber());
        employee.setQuestion(e.getQuestion());
        employee.setAnswer(e.getAnswer());
        employeeDAO.save(employee);
    }
}
