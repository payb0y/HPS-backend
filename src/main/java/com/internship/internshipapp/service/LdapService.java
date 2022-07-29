package com.internship.internshipapp.service;

import org.json.simple.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.*;

@Slf4j
public class LdapService {
    DirContext connection;
    public void newConnection() {
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
        env.put(Context.SECURITY_PRINCIPAL, "uid=admin, ou=system");
        env.put(Context.SECURITY_CREDENTIALS, "secret");
        try {
            connection = new InitialDirContext(env);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<JSONObject> getAllGroups(String username) throws NamingException {
        newConnection();
        String searchFilter = "(objectClass=groupOfUniqueNames)";
        String[] reqAtt = { "cn", "uniqueMember" };
        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        controls.setReturningAttributes(reqAtt);

        NamingEnumeration users = connection.search("ou=groups,dc=example,dc=com", searchFilter, controls);
        List<JSONObject> groups = new ArrayList<>();
        SearchResult result;
        while (users.hasMore()) {
            result = (SearchResult) users.next();
            Attributes attr = result.getAttributes();
            if(attr.get("uniqueMember").toString().contains(username)){
                JSONObject json = new JSONObject();
                json.put("name",attr.get("cn").toString().split("cn:")[1].replaceAll("\\s",""));
                groups.add(json);

            }
        }
        connection.close();

        return groups;
    }
}
