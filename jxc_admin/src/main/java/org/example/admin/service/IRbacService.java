package org.example.admin.service;

import java.util.List;

/**
 * @author 201366530
 */
public interface IRbacService {
    List<String> findRolesByUserName(String userName);


    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
