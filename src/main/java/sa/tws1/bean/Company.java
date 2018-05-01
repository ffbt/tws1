package sa.tws1.bean;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "company_")
public class Company
{
    @Id
    private Integer id;
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")
    private Set<Employee> employees;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")
    private Set<AllTool> allTools;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Employee> getEmployees()
    {
        return employees;
    }

    public void setEmployees(Set<Employee> employees)
    {
        this.employees = employees;
    }

    public Set<AllTool> getAllTools()
    {
        return allTools;
    }

    public void setAllTools(Set<AllTool> allTools)
    {
        this.allTools = allTools;
    }
}
