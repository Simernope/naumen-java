/*
package ru.simeon.NauJava.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.simeon.NauJava.models.Role;
import ru.simeon.NauJava.models.User;

@Component
public class MyInMemoryUserDetailService implements UserDetailsService
{
    private List<User> users = new ArrayList<>();
    public MyInMemoryUserDetailService()
    {
        User user = new User("user", "123", Role.USER);
        users.add(user);
        User admin = new User("admin", "admin", Role.ADMIN);
        users.add(admin);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User appUser = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (appUser != null)
        {
            org.springframework.security.core.userdetails.User user = new
                    org.springframework.security.core.userdetails.User(
                    appUser.getUsername(), appUser.getPassword(),
                    mapRoles(appUser));
            return user;
        }
        else
        {
            throw new UsernameNotFoundException("user not found");
        }
    }
    private Collection<GrantedAuthority> mapRoles(User appUser)
    {
        List<GrantedAuthority> collect = appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" +
                        role.name())).collect(Collectors.toList());
        return collect;
    }
}
*/
