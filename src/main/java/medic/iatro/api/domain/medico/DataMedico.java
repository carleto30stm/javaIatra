package medic.iatro.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medic.iatro.api.domain.addressData.DataAddress;

public record DataMedico(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{4,9}")
        String dni,
        @NotNull
        Specialty specialty,
        @NotNull
        @Valid
        DataAddress address) {

}
