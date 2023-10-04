package medic.iatro.api.domain.addressData;

import jakarta.validation.constraints.NotBlank;

public record DataAddress(
        @NotBlank
        String city,
        @NotBlank
        String street
) {
}
