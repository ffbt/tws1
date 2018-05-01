package sa.tws1.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sa.tws1.bean.Employee;
import sa.tws1.bean.Tool;

public interface ToolDAO extends JpaRepository<Tool, Integer>
{
    Page<Tool> findAllByEmployee(Employee employee, Pageable pageable);
}
