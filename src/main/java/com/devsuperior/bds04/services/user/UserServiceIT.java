package com.devsuperior.bds04.services.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserServiceIT {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
