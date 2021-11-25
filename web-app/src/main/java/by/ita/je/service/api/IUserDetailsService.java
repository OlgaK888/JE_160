package by.ita.je.service.api;

import by.ita.je.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailsService extends UserDetailsService {

    public User getCurrentUser();

}
