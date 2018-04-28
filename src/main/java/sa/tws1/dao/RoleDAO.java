package sa.tws1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.tws1.bean.Role;

import java.util.List;

public interface RoleDAO extends JpaRepository<Role, Integer>
{
    List<Role> findByRoleName(String roleName);
}
