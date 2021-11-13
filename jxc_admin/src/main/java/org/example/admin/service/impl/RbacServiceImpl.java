package org.example.admin.service.impl;

import org.example.admin.service.IRbacService;
import org.example.admin.service.IRoleMenuService;
import org.example.admin.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RbacServiceImpl implements IRbacService {
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public List<String> findRolesByUserName(String userName) {


        return userRoleService.findRolesByUserName(userName);
    }

    @Override
    public List<String> findAuthoritiesByRoleName(List<String> roleNames) {
        return roleMenuService.findAuthoritiesByRoleName(roleNames);
    }
}
