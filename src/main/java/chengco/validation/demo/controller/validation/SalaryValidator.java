package chengco.validation.demo.controller.validation;

import chengco.validation.demo.controller.UserResource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class SalaryValidator implements ConstraintValidator<SalaryConstraint, UserResource> {
    private BigDecimal minSalary;

    @Override
    public void initialize(SalaryConstraint constraintAnnotation) {
        minSalary = BigDecimal.valueOf(constraintAnnotation.value());
    }

    @Override
    public boolean isValid(UserResource user, ConstraintValidatorContext context) {
        boolean isValid = !user.isEmployed() || user.getSalary().compareTo(minSalary) > 0;
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{chengco.validation.demo.controller.validation.SalaryConstraint.message}")
                    .addPropertyNode("salary")
                    .addConstraintViolation();

        }
        return isValid;
    }
}
