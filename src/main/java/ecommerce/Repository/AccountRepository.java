package ecommerce.Repository;

import ecommerce.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);
}
