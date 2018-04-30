package sa.tws1.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sa.tws1.bean.Employee;
import sa.tws1.bean.Role;
import sa.tws1.dao.EmployeeDAO;
import sa.tws1.security.bean.SecurityUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserService implements UserDetailsService
{
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException
    {
        Employee employee;
        try
        {
            employee = employeeDAO.findById(id).get(); // 根据id查找
        }
        catch (Exception e)
        {
            throw new UsernameNotFoundException("id 不存在");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roleSet = employee.getRoles();
        for (Role role : roleSet)
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        return new SecurityUser(employee.getId(), employee.getPassword(), authorities);
    }
}
