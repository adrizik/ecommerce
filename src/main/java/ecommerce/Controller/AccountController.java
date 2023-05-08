package ecommerce.Controller;

import ecommerce.Exceptions.ServiceException;
import ecommerce.Model.Account;
import ecommerce.Service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    /**
     * As a user I should be able to register
     * POST localhost:9000/register
     */
    @PostMapping("register")
    public Account register(@RequestBody Account account) throws ServiceException {
        return accountService.register(account);
    }


    /**
     * As a user I should be able to log in to my account
     * POST localhost:9000/login
     */
    @PostMapping("login")
    public Account login(@RequestBody Account account) throws ServiceException {
        return accountService.login(account);
    }

    /**
     * As an admin I should be able to see all accounts
     * POST localhost:9000/accounts
     */
    @GetMapping("accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }


    /**
     * As a user I should be able to add products to my account
     * POST localhost:9000/{account_id}/add/{product_id}
     */
    @PostMapping("{account_id}/add/{product_id}")
    public Account addProducts(@PathVariable long account_id, @PathVariable long product_id) throws ServiceException {
        return accountService.addProducts(account_id, product_id);
    }

    /**
     * As a user I should be able to checkout.
     * POST localhost:9000/{checkout/{id}
     */
    @PostMapping("checkout/{id}")
    public Account checkout(@PathVariable long id) throws ServiceException {
        return accountService.checkout(id);
    }

}
