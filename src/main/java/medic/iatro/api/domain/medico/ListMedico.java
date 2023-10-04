package medic.iatro.api.domain.medico;

import medic.iatro.api.domain.medico.model.MedicoModel;

public record ListMedico(Long id, String name, String email, String dni, String specialty, String active) {
    public ListMedico(MedicoModel medico) {
        this(medico.getId(), medico.getName(), medico.getEmail(), medico.getDni(), medico.getSpecialty().toString(), medico.getActive().toString());
    }
}
