package ecommerce.Controller;

import ecommerce.Model.Account;
import ecommerce.Repository.AccountRepository;
import ecommerce.Service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class AccountControllerTest {

    @MockBean
    AccountController accountController;
    @Autowired
    MockMvc mockMvc;

    @Test
    void testRegister() throws Exception {

        Account account = new Account(1,
                "peter@gmail.com",
                "orange",
                0.00,
                new ArrayList<>()
        );

        doReturn(account).when(accountController).register(account);

        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:9000/register")
                        .content("{\"id\": 1, \"email\": \"peter@gmail.com\", \"password\": \"orange\", \"balance\": 0.00, \"products\": []}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("peter@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("orange"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value(0.00))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products").isArray());

    }
}