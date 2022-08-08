package net.woden.wdsit.util;
import java.util.Base64;
import org.springframework.stereotype.Service;
/**
 *
 * @author f.casallas
 */
@Service
public class Encription {
    public String encode(String encriptar){
      byte[] decodedBytes = encriptar.getBytes();     
        return Base64.getEncoder().encodeToString(decodedBytes);
    }
    public String decode(String encriptar){
      return new String ((Base64.getDecoder().decode(encriptar)));       
        
    }
    
    
}
    