package by.ita.je.service.api;

import by.ita.je.configuration.MyUserDetails;
import by.ita.je.dao.UserDao;
import by.ita.je.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user=userDao.findByLogin(login);
        if(user == null){
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(user);
    }
}