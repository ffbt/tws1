package sa.tws1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.tws1.bean.Employee;
import sa.tws1.bean.Role;
import sa.tws1.dao.EmployeeDAO;
import sa.tws1.dao.RoleDAO;
import sa.tws1.util.MD5Util;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeUtils
{
    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private RoleDAO roleDAO;

    private static EmployeeUtils employeeUtils;

    @PostConstruct
    public void init()
    {
        employeeUtils = this;
        employeeDAO = this.employeeDAO;
        roleDAO = this.roleDAO;
    }

    private final static String[] roles = new String[]{"ROLE_COMMON", "ROLE_ADMINISTRATORS", "ROLE_SPECIALIST", "ROLE_EMPLOYEE"};
    private final static String[] companys = new String[]{"ParentCompany", "SubCompany1", "SubCompany2", "SubCompany3", "SubCompany4",
            "SubCompany5", "SubCompany6", "SubCompany7", "SubCompany8"};
    private final static String[] departments = new String[]{"", "ConstructionDeviceRepair", "AutomobileRepair", "ApplianceRepair", "ComputerRepair"};
    private final static int ROLE_START = 0, ROLE_END = 1;
    private final static int COMPANY_START = 1, COMPANY_END = 2;
    private final static int DEPARTMENT_START = 2, DEPARTMENT_END = 3;

    public static boolean verifyEmployee(Employee employee)
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

    private static void save(int i, Employee e)
    {
        List<Role> roleList = employeeUtils.roleDAO.findByRoleName(roles[i]);
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
        employeeUtils.roleDAO.save(role);
    }
}
