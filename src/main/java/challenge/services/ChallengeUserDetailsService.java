package challenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by gaopeng on 5/12/17.
 */
@Service
public class ChallengeUserDetailsService implements UserDetailsService {

    @Autowired
    private IChallengeDAO challengeDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return challengeDAO.loadUserByUsername(username);

    }
}
