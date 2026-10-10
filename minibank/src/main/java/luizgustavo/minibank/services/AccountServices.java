package luizgustavo.minibank.services;


import luizgustavo.minibank.model.Account;
import luizgustavo.minibank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServices {
    private final AccountRepository accountRepository;

    public AccountServices(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

//METODO DESENVOLVIDO APENAS PARA TESTES
    public List<Account> list(){
        return accountRepository.findAll();
    }

    public List<Account> create(Account account) {
        accountRepository.save(account);
        return list();
    }
    public Account getById(Long id){
        return accountRepository.findById(id).orElseThrow(()
                -> new RuntimeException("account not found" + id));
    }
    public Account deposit(Long id, BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("amount must be greater than zero");}

        Account account = accountRepository.findById(id).orElseThrow(()
                -> new RuntimeException("account not found" + id));

        account.setAmount(account.getAmount().add(amount));

        return accountRepository.save(account);

    }

    public Account withdraw(Long id, BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("amount must be greater than zero");
        }

        Account  account = accountRepository.findById(id).orElseThrow(()
                -> new RuntimeException("account not found" + id));

        account.setAmount(account.getAmount().subtract(amount));

        return accountRepository.save(account);
    }





}
