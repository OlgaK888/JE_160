package by.ita.je.service;

import by.ita.je.dao.AccountDAO;
import by.ita.je.model.Account;
import by.ita.je.service.api.AccountService;
import by.ita.je.exception.NotFoundDataException;
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
