package luizgustavo.minibank.repository;

import luizgustavo.minibank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
