/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

import io.fourfinanceit.homework.Application;
import io.fourfinanceit.homework.risk.Loan;
import io.fourfinanceit.homework.risk.LoanEntity;
import io.fourfinanceit.homework.risk.LoanRepository;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.is;

/**
 *
 * @author bosone
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class LoanControllerTest {

    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private LoanEntity firstLoan;
    private LoanEntity secondLoan;
    private LoanEntity thirdLoan;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("Must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setUp() {
        firstLoan = new LoanEntity();
        firstLoan.convertLoanToEntity(new Loan("Zhanibek", "Kenzheyev", 37, 12, LocalDateTime.now(), "127.0.0.1"));
        secondLoan = new LoanEntity();
        secondLoan.convertLoanToEntity(new Loan("Zhanibek2", "Kenzheyev2", 50, 24, LocalDateTime.now(), "127.0.0.1"));
        thirdLoan = new LoanEntity();
        thirdLoan.convertLoanToEntity(new Loan("Zhanibek3", "Kenzheyev3", 100, 6, LocalDateTime.now(), "127.0.0.1"));

        loanRepository.deleteAll();
        loanRepository.save(Arrays.asList(firstLoan, secondLoan, thirdLoan));

        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

    }

    /*
    * Reject when creating loan, because there was three loans from one IP-address
     */
    @Test
    public void isRejectExceedNumberPerDay() throws Exception {
        this.mockMvc
                .perform(post("/create")
                        .contentType(this.contentType)
                        .content(this.json(new Loan("Zhanibek4", "Kenzheyev4", 60, 10, LocalDateTime.now(), "127.0.0.1"))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.success", is(false)));
    }

    /*
    * Reject when creating loan, because the attempt to take loan is not made between 00:00 to 06:00 AM
     */
    @Test
    public void isRejectAttemptTakeLoan() throws Exception {
        this.mockMvc
                .perform(post("/create")
                        .contentType(this.contentType)
                        .content(this.json(new Loan("Zhanibek", "Kenzheyev", 99, 10, LocalDateTime.of(LocalDate.now(), LocalTime.of(6, 0, 0, 0)), "127.0.0.1"))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.success", is(false)));
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
