package sa.tws1.service.wms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.tws1.bean.AllTool;
import sa.tws1.bean.Employee;
import sa.tws1.dao.AllToolDAO;
import sa.tws1.service.etms.EmployeeService;
import sa.tws1.service.etms.ToolService;
import sa.tws1.util.CommonUtil;

import java.util.List;

@Service
public class WMSService
{
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AllToolDAO allToolDAO;

    @Autowired
    private ToolService toolService;

    private ToolWarehouse toolWarehouse = new ToolWarehouse();

    public int lend(String toolName)
    {
        Employee employee = employeeService.getEmployee();
        List<AllTool> list = allToolDAO.findByNameAndCompany(toolName, employee.getCompany());
        return verifyAndLend(employee, list);
    }

    public int lendRemote(String toolName)
    {
        Employee employee = employeeService.getEmployee();
        List<AllTool> list = allToolDAO.findByName(toolName);
        return verifyAndLend(employee, list);
    }

    private int verifyAndLend(Employee employee, List<AllTool> list)
    {
        if (list.isEmpty())
            return 1;   // 没有
        AllTool allTool = list.get(0);
        if (employee.getUserName().equals(CommonUtil.roles[CommonUtil.specialistId]))
        {
            toolService.registerTool(employee, allTool);
            toolWarehouse.getTool(allTool);
            return 0;   // ok
        }
        for (AllTool allTool1 : list)
        {
            if (allTool1.getDepartment().equals(employee.getDepartment()))
            {
                toolService.registerTool(employee, allTool1);
                toolWarehouse.getTool(allTool);
                return 0;   // ok
            }
        }
        return 2;   // 没有权限
    }
}
