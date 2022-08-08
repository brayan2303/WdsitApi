package net.woden.wdsit.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.annotation.Resource;
import net.woden.wdsit.entity.GenPersonEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerateToken {

    
    @Value("${jwt.secret}")
    private String SECRET;
    
    @Resource
    private UserRepositoryToken userRepository;

    public String getToken(GenPersonEntity g) {
        String generateToken = "";
        //Token de inicio de sesión
        try{
            Claims token = Jwts
                    .claims()
                    .setSubject(g.getUserName())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    // Expiración token x cantidad tiempo en Milisegundos (8 horas)
                    .setExpiration(new Date(System.currentTimeMillis() + 28800000));
            token.put("Id", String.valueOf(g.getId()));
            // Creación Token con el HASH, la clave y el id del usuario 
            generateToken = Jwts.builder()
                    .setClaims(token)
                    .signWith(SignatureAlgorithm.HS512, this.SECRET)
                    .compact();
            userRepository.insert(g.getUserName());
            return "Bearer " + generateToken;
        } catch(Exception ex){
            return null;
        }
    }
}
