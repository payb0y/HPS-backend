package com.internship.internshipapp.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.internshipapp.domain.User;
import com.internship.internshipapp.service.LdapService;
import com.internship.internshipapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Utility {
    private static LdapService ldapService = new LdapService();
    public static List<Object>  appendLdapGroupsToUsers(List<User> users){
        List<Object> newUsers = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        users.forEach(user-> {
            try {
                Map<String, Object> map = objectMapper.convertValue(user, Map.class);
                map.put("ldapGroups",ldapService.getAllGroups(user.getUsername()));
                newUsers.add(map);
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }});
        return newUsers;
    }
}
