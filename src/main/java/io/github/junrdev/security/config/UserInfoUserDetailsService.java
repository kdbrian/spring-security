package io.github.junrdev.security.config;

import io.github.junrdev.security.entity.UserInfo;
import io.github.junrdev.security.repo.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userInfoRepo.findByName(username)
                ;

        // map user to user details
        return user.map(UserInfUserDetails::new)
                .orElseThrow(RuntimeException::new);
    }
}
