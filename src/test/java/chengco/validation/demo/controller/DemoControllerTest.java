package chengco.validation.demo.controller;

import chengco.validation.demo.controller.ExceptionController.ErrorMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoControllerTest extends TestCase {
    private MockMvc mockMvc;
    @Autowired
    public WebApplicationContext context;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test_validation() throws Exception {
        DemoResource demoResource = DemoResource.builder()
                .user(UserResource.builder()
                        .age(500)
                        .name(" ")
                        .email("email")
                        .build())
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        MvcResult mvcResult = mockMvc.perform(post("/testValidation")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(demoResource)))
                .andExpect(status().isInternalServerError())
                .andReturn();

        List<ErrorMessage> result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<ErrorMessage>>(){});

        assertThat(result, hasItems(
                errorMessage("user.age", "must be less than or equal to 200"),
                errorMessage("user.name", "must not be blank"),
                errorMessage("user.email", "must be a well-formed email address"),
                errorMessage("user.id", "must not be null")
        ));
    }

    private ErrorMessage errorMessage(String field, String message) {
        return ErrorMessage.builder()
                .filed(field)
                .message(message)
                .build();
    }
}
