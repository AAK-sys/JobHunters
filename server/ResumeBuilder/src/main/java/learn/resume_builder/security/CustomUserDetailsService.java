package learn.resume_builder.security;

import learn.resume_builder.data.AppUserJdbcRepository;
import learn.resume_builder.data.UserJdbcTemplateRepository;
import learn.resume_builder.data.UserRepository;
import learn.resume_builder.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private AppUserJdbcRepository repo;

    @Autowired
    public CustomUserDetailsService(AppUserJdbcRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        learn.resume_builder.models.User user = repo.findByUsername(s);

        if (user == null || user.isDisabled()) {
            throw new UsernameNotFoundException("User not found with username: " + s);
        }
        Collection<GrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
