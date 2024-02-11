package sh.alex.onlineTesting.Safety;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import sh.alex.onlineTesting.model.entities.users.User;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JwtUtil {

    // Секретный ключ для подписи JWT
    private static final String SECRET_KEY = "my-secret-key-without-using-application.yaml-value";

    // Время действия JWT (1 час)
    private static final long EXPIRATION_TIME = 3600000;

    // Генерация JWT-токена на основе данных пользователя
    public static String generateToken(User user) {
        // Установка времени создания и истечения срока действия токена
        long currentTime = System.currentTimeMillis();
        long expirationTime = currentTime + EXPIRATION_TIME;

        return Jwts.builder()
                .claim("userId", user.getId())
                .claim("username", user.getUserName())
                .claim("roles", user.getRoles())
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Валидация JWT-токена и получение данных пользователя
    public static User validateToken(String token) {
        try {
            // Проверка подписи токена
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);

            // Извлечение данных из токена
            Long userId = claims.getBody().get("userId", Long.class);
            String username = claims.getBody().get("username", String.class);
            Set<User.Role> roles = extractRoles(claims.getBody().get("roles", List.class));

            // Создание объекта User
            User user = new User();
            user.setId(userId);
            user.setUserName(username);
            user.setRoles(roles);

            return user;

        } catch (Exception e) {
            return null;
        }
    }

    // Извлечение ролей пользователя из токена
    private static Set<User.Role> extractRoles(List<String> roleList) {
        Set<User.Role> roles = new HashSet<>();
        if (roleList != null) {
            for (String role : roleList) {
                roles.add(User.Role.valueOf(role));
            }
        }
        return roles;
    }
}
