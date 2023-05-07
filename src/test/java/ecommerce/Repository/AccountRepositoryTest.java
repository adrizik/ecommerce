package ecommerce.Repository;

import ecommerce.Model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findExistingEmail() {
        //Given
        String email = "pedro@gmail.com";
        Account account = new Account(1,
                email,
                "orange",
                0.00,
                new ArrayList<>()
        );

        underTest.save(account);

        //When
        Account expected = underTest.findByEmail(email);

        //Then
        Assertions.assertEquals(account, expected);
    }

    @Test
    void emailDoesNotExist() {
        //Given
        String email = "doesnot_exist@gmail.com";

        //When
        Account expected = underTest.findByEmail(email);

        //Then
        Assertions.assertNull(expected);
    }
}