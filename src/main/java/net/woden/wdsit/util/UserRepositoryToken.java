/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.woden.wdsit.util;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author m.pulido
 */
@Component
public class UserRepositoryToken {
    
    private static final Map<String,String> userMap = new HashMap<String,String>();

    public String findByUsername(final String username){
        return userMap.get(username);
    }

    public String insert(String user){
        userMap.put(user,user);
        return user;
    }

    public void remove(String username){
        userMap.remove(username);
    }
    
}
