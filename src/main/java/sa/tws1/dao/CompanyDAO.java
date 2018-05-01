package sa.tws1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.tws1.bean.Company;

public interface CompanyDAO extends JpaRepository<Company, Integer>
{
}
