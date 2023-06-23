package com.devsuperior.bds04.services.user;

import com.devsuperior.bds04.entities.User;
import com.devsuperior.bds04.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.devsuperior.bds04.controllers.exeptions.Utils.*;

@Service
public class UserServiceImpl implements UserDetailsService, UserServiceIT {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByEmail(username);
        if (user == null) {
            logger.error(USER_NOT_FOUND + username);
            throw new UsernameNotFoundException(EMAIL_NOT_FOUND + username);
        }
        logger.info(EMAIL_FOUND + username);
        return user;
    }
}
