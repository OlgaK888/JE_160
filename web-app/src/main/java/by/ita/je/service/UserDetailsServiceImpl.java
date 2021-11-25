package by.ita.je.service;

import by.ita.je.configuration.MyUserDetails;
import by.ita.je.dao.UserDao;
import by.ita.je.models.User;
import by.ita.je.service.api.IUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements IUserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findByLogin(login);
        if(user == null){
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(user);
    }

    @Transactional(rollbackFor=Exception.class)
    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDao.findByLogin(auth.getName());
    }
}