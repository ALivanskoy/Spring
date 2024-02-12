package sh.alex.onlineTesting.Safety;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import sh.alex.onlineTesting.model.entities.users.User;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JwtUtil {

    private static final String SECRET_KEY = "my-secret-key-without-using-application.yaml-value";
    private static final long EXPIRATION_TIME = 3600000;

    public static String generateToken(User user) {
        long currentTime = System.currentTimeMillis();
        long expirationTime = currentTime + EXPIRATION_TIME;

        return Jwts.builder()
                .claim("userId", user.getId())
                .claim("username", user.getUsername())
                .claim("roles", extractRoles(user.getRoles().stream().toList()))
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static User validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);

            Long userId = claims.getBody().get("userId", Long.class);
            String username = claims.getBody().get("username", String.class);
            Set<User.Role> roles = extractRoles(claims.getBody().get("roles", List.class));

            User user = new User();
            user.setId(userId);
            user.setUsername(username);
            user.setRoles(roles);

            return user;

        } catch (Exception e) {
            return null;
        }
    }

    private static Set<User.Role> extractRoles(List<?> roleList) {
        Set<User.Role> roles = new HashSet<>();
        if (roleList != null) {
            for (Object role : roleList) {
                roles.add(User.Role.valueOf(role.toString()));
            }
        }
        return roles;
    }
}


