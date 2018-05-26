package sa.tws1.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sa.tws1.bean.AllTool;
import sa.tws1.bean.Company;

import java.util.List;

public interface AllToolDAO extends JpaRepository<AllTool, Integer>
{
    Page<AllTool> findByCompanyAndDepartmentAndPriceIsLessThanEqual(Company company, String department, double price, Pageable pageable);

    Page<AllTool> findByCompanyAndDepartmentAndPriceIsGreaterThan(Company company, String department, double price, Pageable pageable);

    Page<AllTool> findByCompanyAndPriceIsLessThanEqual(Company company, double price, Pageable pageable);

    Page<AllTool> findByCompanyAndPriceIsGreaterThan(Company company, double price, Pageable pageable);

    List<AllTool> findByNameAndCompany(String name, Company company);

    List<AllTool> findByName(String name);

    Page<AllTool> findAllByCompany(Company company, Pageable pageable);

    AllTool findByNameAndCompanyAndDepartmentAndPrice(String name, Company company, String department, double price);
}
