package com.cineflix.vn.service.modelService.serviceiplm;

import com.cineflix.vn.model.Enum.UserRole;
import com.cineflix.vn.model.Role;
import com.cineflix.vn.model.dao.RoleDAO;
import com.cineflix.vn.service.modelService.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;

    @Transactional
    @Override
    public void updateRoleAutomatic() {
        Arrays.stream(UserRole.values()).forEach(userRole -> {
            Role role = new Role(
                    userRole.getIdRole(),
                    userRole.getRoleName(),
                    userRole.getDescription()
            );
            roleDAO.save(role);
        });
    }
}
