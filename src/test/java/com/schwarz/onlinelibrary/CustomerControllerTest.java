package com.schwarz.onlinelibrary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schwarz.onlinelibrary.model.CustomerDto;
import com.schwarz.onlinelibrary.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Test
    public void testFindCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setDeposit(50.0);
        customerDto.setEmail("test@gmail.com");
        customerDto.setFirstName("first");
        customerDto.setLastName("last");
        customerDto.setPassword("password");

        customerService.saveCustomer(customerDto);
        mockMvc.perform(get("/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("first"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("last"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("password"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deposit").value(50.0));
    }

    @Test
    public void testFindAllCustomers() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setDeposit(50.0);
        customerDto.setEmail("test@gmail.com");
        customerDto.setFirstName("first");
        customerDto.setLastName("last");
        customerDto.setPassword("password");

        CustomerDto customerDto2 = new CustomerDto();
        customerDto2.setDeposit(50.0);
        customerDto2.setEmail("tes2t@gmail.com");
        customerDto2.setFirstName("other");
        customerDto2.setLastName("other");
        customerDto2.setPassword("password2");

        customerService.saveCustomer(customerDto);
        customerService.saveCustomer(customerDto2);

        mockMvc.perform(get("/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].firstName").value("first"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("last"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("test@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("password"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].deposit").value(50.0))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("other"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("other"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("tes2t@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].password").value("password2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].deposit").value(50.0));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setDeposit(50.0);
        customerDto.setEmail("test@gmail.com");
        customerDto.setFirstName("first");
        customerDto.setLastName("last");
        customerDto.setPassword("password");
        mockMvc.perform( MockMvcRequestBuilders
                .post("/customers")
                .content(asJsonString(customerDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("first"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("last"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("test@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("password"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deposit").value(50.0));

        assertEquals("test@gmail.com", customerService.findCustomer(1L).getEmail());
        customerService.deleteCustomer(1L);
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setDeposit(50.0);
        customerDto.setEmail("test@gmail.com");
        customerDto.setFirstName("first");
        customerDto.setLastName("last");
        customerDto.setPassword("password");

        customerService.saveCustomer(customerDto);

        customerDto.setEmail("changedEmail@gmail.com");
        mockMvc.perform( MockMvcRequestBuilders
                .put("/customers/1")
                .content(asJsonString(customerDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("first"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("last"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("changedEmail@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("password"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deposit").value(50.0));

        assertEquals("changedEmail@gmail.com", customerService.findCustomer(1L).getEmail());
        customerService.deleteCustomer(1L);
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setDeposit(50.0);
        customerDto.setEmail("test@gmail.com");
        customerDto.setFirstName("first");
        customerDto.setLastName("last");
        customerDto.setPassword("password");

        customerService.saveCustomer(customerDto);

        customerDto.setEmail("changedEmail@gmail.com");
        mockMvc.perform( MockMvcRequestBuilders
                .delete("/customers/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(0, customerService.findAllCustomers().size());
    }

    @Test
    public void testCreateCustomerInvalidEmail() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setDeposit(50.0);
        customerDto.setEmail("invalidemail");
        customerDto.setFirstName("first");
        customerDto.setLastName("last");
        customerDto.setPassword("password");
        mockMvc.perform( MockMvcRequestBuilders
                .post("/customers")
                .content(asJsonString(customerDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
