package chengco.validation.demo.controller;

import chengco.validation.demo.controller.validation.SalaryConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@SalaryConstraint(1000)
public class UserResource {
    @NotNull
    private UUID id;
    @NotBlank
    private String name;
    @Max(value = 200)
    @Min(value = 1)
    private int age;
    private boolean gender;
    @Email
    private String email;
    private boolean employed;
    private BigDecimal salary;
}
