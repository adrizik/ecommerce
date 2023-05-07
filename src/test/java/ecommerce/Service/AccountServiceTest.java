package ecommerce.Service;

import ecommerce.Exceptions.ServiceException;
import ecommerce.Model.Account;
import ecommerce.Repository.AccountRepository;
import ecommerce.Repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ProductRepository productRepository;
    private AutoCloseable autoCloseable;
    private AccountService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AccountService(accountRepository, productRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canRegister() throws ServiceException {

        //Given
        Account account = new Account(1,
                "peter@gmail.com",
                "orange",
                0.00,
                new ArrayList<>()
        );

        //When
        underTest.register(account);

        //Then
        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);

        verify(accountRepository).save(accountArgumentCaptor.capture());

        Account capturedAccount = accountArgumentCaptor.getValue();

        Assertions.assertEquals(account, capturedAccount);

    }

    @Test
    void duplicateAccount() throws ServiceException {

        //Given
        Account account = new Account(1,
                "peter@gmail.com",
                "orange",
                0.00,
                new ArrayList<>()
        );

        given(accountRepository.findByEmail(account.getEmail()))
                .willReturn(account);

        // When
        Throwable exception = Assertions.assertThrows(ServiceException.class, () -> underTest.register(account));

        // Then
        Assertions.assertEquals("Account already exists", exception.getMessage());

        verify(accountRepository, never()).save(any());
    }

    @Test
    void ableToLogin() throws ServiceException {

        //Given
        String email = "pedro@gmail.com";
        Account account = new Account(1,
                email,
                "orange",
                0.00,
                new ArrayList<>()
        );

        given(accountRepository.findByEmail(account.getEmail()))
                .willReturn(account);

        //When
        underTest.login(account);
        Account expected = accountRepository.findByEmail(email);

        //Then
        Assertions.assertEquals(account, expected);
    }
}