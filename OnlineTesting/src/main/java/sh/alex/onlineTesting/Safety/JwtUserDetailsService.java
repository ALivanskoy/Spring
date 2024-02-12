package sh.alex.onlineTesting.Safety;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sh.alex.onlineTesting.model.entities.users.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    //Обрабатывает аутентификации и авторизации пользователей на основе токенов JWT
    @Override
    @Bean
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        User user = JwtUtil.validateToken(token);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid token");
        }

        //Создание списка ролей пользователя
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (User.Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), "", authorities);
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

