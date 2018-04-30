package sa.tws1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sa.tws1.bean.Employee;
import sa.tws1.bean.Role;
import sa.tws1.dao.EmployeeDAO;
import sa.tws1.dao.RoleDAO;
import sa.tws1.util.MD5Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private RoleDAO roleDAO;

    private static Employee employee;

    private static final String[] roles = new String[]{"ROLE_COMMON", "ROLE_ADMINISTRATORS", "ROLE_SPECIALIST", "ROLE_EMPLOYEE"};
    private static final String[] companys = new String[]{"ParentCompany", "SubCompany1", "SubCompany2", "SubCompany3", "SubCompany4",
            "SubCompany5", "SubCompany6", "SubCompany7", "SubCompany8"};
    private static final String[] departments = new String[]{"", "ConstructionDeviceRepair", "AutomobileRepair", "ApplianceRepair", "ComputerRepair"};
    private static final int ROLE_START = 0, ROLE_END = 1;
    private static final int COMPANY_START = 1, COMPANY_END = 2;
    private static final int DEPARTMENT_START = 2, DEPARTMENT_END = 3;

    public boolean verifyEmployee(Employee employee)
    {
        String id = employee.getId();
        try
        {
            int i = Integer.parseInt(id.substring(ROLE_START, ROLE_END));
            int company = Integer.parseInt(id.substring(COMPANY_START, COMPANY_END));
            int department = Integer.parseInt(id.substring(DEPARTMENT_START, DEPARTMENT_END));
            employee.setPassword(MD5Util.encode(employee.getPassword()));
            employee.setCompany(companys[company]);
            employee.setDepartment(departments[department]);
            save(0, employee);
            save(i, employee);
            if (i == 2)
                save(3, employee);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    private void save(int i, Employee e)
    {
        List<Role> roleList = roleDAO.findByRoleName(roles[i]);
        Role role;
        if (roleList.isEmpty())
        {
            role = new Role();
            role.setRoleName(roles[i]);
            Set<Employee> employeeSet = new HashSet<>();
            employeeSet.add(e);
            role.setEmployees(employeeSet);
        }
        else
        {
            assert (roleList.size() == 1);
            role = roleList.get(0);
            role.getEmployees().add(e);
        }
        roleDAO.save(role);
    }

    public boolean verifyPassword(String oldPassword, String newPassword)
    {
        if (employee.getPassword().equals(MD5Util.encode(oldPassword)))
        {
            employee.setPassword(MD5Util.encode(newPassword));
            employeeDAO.save(employee);
            return true;
        }
        return false;
    }

    public void setEmployee()
    {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        employee = employeeDAO.findById(userDetails.getUsername()).get();
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void updateMessage(Employee e)
    {
        employee.setUserName(e.getUserName());
        employee.setEmail(e.getEmail());
        employee.setPhoneNumber(e.getPhoneNumber());
        employee.setQuestion(e.getQuestion());
        employee.setAnswer(e.getAnswer());
        employeeDAO.save(employee);
    }
}
