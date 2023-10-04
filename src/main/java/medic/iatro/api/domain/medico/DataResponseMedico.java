package medic.iatro.api.domain.medico;

import medic.iatro.api.domain.addressData.DataAddress;

public record DataResponseMedico(String name, String email, String dni, String specialty, String active, DataAddress address) {
}
