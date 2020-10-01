package chengco.validation.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
