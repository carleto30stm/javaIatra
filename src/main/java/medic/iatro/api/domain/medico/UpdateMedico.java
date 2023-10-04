package medic.iatro.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import medic.iatro.api.domain.addressData.DataAddress;

public record UpdateMedico(@NotNull Long id, String name, String dni, DataAddress address) {
    public class Address extends medic.iatro.api.domain.addressData.Address {
    }
}
