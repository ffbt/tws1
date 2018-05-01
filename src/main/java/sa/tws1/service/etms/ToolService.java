package sa.tws1.service.etms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sa.tws1.bean.*;
import sa.tws1.dao.AllToolDAO;
import sa.tws1.dao.CompanyDAO;
import sa.tws1.dao.EmployeeDAO;
import sa.tws1.dao.ToolDAO;
import sa.tws1.util.CommonUtil;

import java.util.*;

@Service
public class ToolService
{
    @Autowired
    private ToolDAO toolDAO;

    @Autowired
    private AllToolDAO allToolDAO;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    public Page<AllTool> getInexpensiveTools(int start, int size)
    {
        Employee employee = employeeService.getEmployee();
        start = start < 0 ? 0 : start;
        double price = 200;
        Set<Role> roleSet = employeeService.getEmployee().getRoles();
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<AllTool> page;
        boolean flag = false;
        for (Role role : roleSet)
        {
            if (role.getRoleName().equals(CommonUtil.roles[CommonUtil.specialistId]))
            {
                flag = true;
                break;
            }
        }
        if (flag)
            page = allToolDAO.findByCompanyAndPriceIsLessThanEqual(employee.getCompany(), price, pageable);
        else
            page = allToolDAO.findByCompanyAndDepartmentAndPriceIsLessThanEqual(employee.getCompany(), employee.getDepartment(), price, pageable);
        return page;
    }

    public Page<AllTool> getAllTools(int start, int size)
    {
        Employee employee = employeeService.getEmployee();
        start = start < 0 ? 0 : start;
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        return allToolDAO.findAllByCompany(employee.getCompany(), pageable);
    }

    public void oneKeyAddition()
    {
        Random random = new Random();
        int n = 1000;
        Map<Integer, Company> map = new HashMap<>();
        List<AllTool> list = new ArrayList<>();
        for (int i = 0; i < n; i++)
        {
            Company company;
            AllTool allTool = new AllTool();
            allTool.setName("tool" + i);
            int companyId = random.nextInt(9);
            int departmentId = 1 + random.nextInt(4);
            int num = 50 + random.nextInt(50);
            double price = random.nextDouble() * 400;
            if ((company = map.get(companyId)) == null)
            {
                company = companyDAO.findById(companyId).get();
                if (company.getAllTools() == null)
                    company.setAllTools(new HashSet<>());
                map.put(companyId, company);
            }
            company.getAllTools().add(allTool);
            allTool.setDepartment(CommonUtil.departments[departmentId]);
            allTool.setNum(num);
            allTool.setPrice(price);
            list.add(allTool);
        }
        allToolDAO.saveAll(list);
        companyDAO.saveAll(map.values());
    }

    public void registerTool(Employee employee, AllTool allTool)
    {
        Tool tool = new Tool();
        tool.setTid(allTool.getId());
        tool.setNum(1);
        tool.setDate(new Date());
        toolDAO.save(tool);

        if (employee.getTools() == null)
            employee.setTools(new HashSet<>());
        employee.getTools().add(tool);
        employeeDAO.save(employee);

        allTool.setNum(allTool.getNum() - 1);
        if (allTool.getNum() == 0)
            allToolDAO.delete(allTool);
        else
            allToolDAO.save(allTool);
    }

    public void registerInexpensiveTool(int id)
    {
        Employee employee = employeeService.getEmployee();
        AllTool allTool = allToolDAO.findById(id).get();
        registerTool(employee, allTool);
    }

    public Page<Tool> queryBorrowTools(int start, int size)
    {
        start = start < 0 ? 0 : start;
        Employee employee = employeeService.getEmployee();
        Sort sort = new Sort(Sort.Direction.ASC, "date");
        Pageable pageable = PageRequest.of(start, size, sort);
        Page<Tool> page = toolDAO.findAllByEmployee(employee, pageable);
        return page;
    }

    public List<ToolMessage> getToolMessage(Page<Tool> page)
    {
        List<ToolMessage> list = new ArrayList<>();
        for (Tool tool : page)
        {
            AllTool allTool = allToolDAO.findById(tool.getTid()).get();
            ToolMessage toolMessage = new ToolMessage(tool.getId(), allTool.getName(), tool.getDate(), tool.getNum());
            list.add(toolMessage);
        }
        return list;
    }

    public void cancellation(int id)
    {
        Tool tool = toolDAO.findById(id).get();
        toolDAO.delete(tool);
    }
}
