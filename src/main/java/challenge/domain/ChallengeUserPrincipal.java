package challenge.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by gaopeng on 5/12/17.
 */
public class ChallengeUserPrincipal implements UserDetails{

    private final Person user;
    private final Collection<SimpleGrantedAuthority> role;

    public Person getPerson() {
        return user;
    }

    public Collection<SimpleGrantedAuthority> getRole() {
        return role;
    }

    public ChallengeUserPrincipal(Person user) {
        role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority("USER"));
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        //dummy empty password
        return new BCryptPasswordEncoder().encode("");
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
