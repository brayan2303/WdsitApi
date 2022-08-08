package net.woden.wdsit.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author f.casallas
 */
@Service
public class PasswordEncoder{
    public String encode(String password) {
        
        BCryptPasswordEncoder passwordEncoder   = new BCryptPasswordEncoder(5);
        String encoder = passwordEncoder.encode(password);
        return  encoder;
    }
    
    public boolean match(CharSequence password, String encodedPass){
        BCryptPasswordEncoder passwordEncoder   = new BCryptPasswordEncoder(5);
        boolean result = passwordEncoder.matches(password, encodedPass); 
        return result;
        
    }
}
