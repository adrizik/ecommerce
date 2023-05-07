package ecommerce.Service;

import ecommerce.Exceptions.ServiceException;
import ecommerce.Model.Account;
import ecommerce.Model.Product;
import ecommerce.Repository.AccountRepository;
import ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    AccountRepository accountRepository;
    ProductRepository productRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, ProductRepository productRepository) {
        this.accountRepository = accountRepository;
        this.productRepository = productRepository;
    }

    /**
     * Register
     */

    public Account register(Account account) throws ServiceException {

        if (accountRepository.findByEmail(account.getEmail()) != null){
            throw new ServiceException("Account already exists");
        }

        return accountRepository.save(account);
    }

    /**
     * Login
     */
    public Account login(Account account) throws ServiceException {
        Account accountCredentials = accountRepository.findByEmail(account.getEmail());

        if(accountCredentials == null) {
            throw new ServiceException("Invalid username or password");
        }

        List<Product> accProducts = new ArrayList<>();
        accountCredentials.setProducts(accProducts);


        if (!accountCredentials.getPassword().equals(account.getPassword())) {
            throw new ServiceException("Invalid username or password");
        }


        return accountCredentials;

    }

    public Account addProducts(long accountId, long productId) throws ServiceException{
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        Optional<Product> productOptional = productRepository.findById(productId);

        if (accountOptional.isPresent() && productOptional.isPresent()) {
            Account account = accountOptional.get();
            Product product = productOptional.get();

            List<Product> products = account.getProducts();
            products.add(product);

            account.setProducts(products);

            account.setBalance(account.getBalance() + product.getPrice());

            return accountRepository.save(account);

        } else {
            throw new ServiceException("Account or product not found.");
        }

    }

    public List<Account> getAllAccounts() {
        List<Product> accProducts = new ArrayList<>();
        return accountRepository.findAll();
    }

    public Account checkout(long id) throws ServiceException {
        Optional<Account> findAccount = accountRepository.findById(id);

        if(findAccount.isPresent()) {
            Account account = findAccount.get();
            account.setBalance(0);
            account.setProducts(new ArrayList<>());
            accountRepository.save(account);
            return account;
        }else {
            throw new ServiceException("Account not found.");
        }
    }
}
