package luizgustavo.minibank.controller;

import luizgustavo.minibank.dto.TransactionRequest;
import luizgustavo.minibank.model.Account;
import luizgustavo.minibank.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService  accountService;

    public AccountController(AccountService accountService){this.accountService = accountService;}


    @PostMapping
    List<Account> create(@RequestBody Account account){return accountService.create(account);}

    @GetMapping
    List<Account> list(){return accountService.list();}

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(@PathVariable Long id){
        Account account = accountService.getById(id);
        return ResponseEntity.ok(account);
    }

    @PatchMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestBody TransactionRequest request){
        Account updateAccount = accountService.deposit(id,request.amount());
        return ResponseEntity.ok(updateAccount);

    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long id, @RequestBody TransactionRequest request){
        Account updateAccount = accountService.withdraw(id,request.amount());
        return ResponseEntity.ok(updateAccount);
    }



}
