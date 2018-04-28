package sa.tws1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.tws1.bean.Employee;

public interface EmployeeDAO extends JpaRepository<Employee, String>
{
}
