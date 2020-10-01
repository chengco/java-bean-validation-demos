package chengco.validation.demo.controller;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

public class UserResourceValidationTest {
    private Validator validator;

    @Before
    public void before(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    @Test
    public void test_validation(){
        UserResource user = UserResource.builder()
                .age(500)
                .name(" ")
                .email("email")
                .employed(true)
                .salary(BigDecimal.TEN)
                .build();

        List<String> result = validator.validate(user).stream()
                .map(c -> c.getPropertyPath() + " - " + c.getMessage())
                .collect(Collectors.toList());

        assertThat(result, hasItems(
                "age - must be less than or equal to 200",
                "name - must not be blank",
                "email - must be a well-formed email address",
                "id - must not be null",
                "salary - Salary invalid"
        ));
    }
}
