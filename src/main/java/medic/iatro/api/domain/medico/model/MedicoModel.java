package medic.iatro.api.domain.medico.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import medic.iatro.api.domain.addressData.Address;
import medic.iatro.api.domain.medico.DataMedico;
import medic.iatro.api.domain.medico.Specialty;
import medic.iatro.api.domain.medico.UpdateMedico;

@Table(name = "medicos")
@Entity(name = "MedicoModel")
@Getter
@EqualsAndHashCode(of = "id")

public class MedicoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    private  String email;
    private String dni;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;
    private Boolean active;

    public MedicoModel(DataMedico medico) {
        this.active = true;
        this.name = medico.name();
        this.email = medico.email();
        this.dni = medico.dni();
        this.specialty = medico.specialty();
        this.address = new Address(medico.address());
    }

    public MedicoModel() {
    }

    public void updateData(UpdateMedico updateMedico) {
        if (updateMedico.name() != null){
            this.name = updateMedico.name();
        }
        if (updateMedico.dni() != null){
            this.dni = updateMedico.dni();
        }
        if (updateMedico.address() != null){
            this.address = new Address(updateMedico.address());
        }
    }

    public void desactive(MedicoModel medico) {
        this.active = false;
    }

}
