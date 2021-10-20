package je.service;

import je.dao.AccountDAO;
import je.exception.NotFoundDataException;
import je.model.Account;
import je.service.api.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountDAO accountDAO;

    @Override
    public Account findById(Long id) throws NotFoundDataException {
        final Account account = accountDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Account with id = " + id + " is not found"));
        return account;
    }
}
