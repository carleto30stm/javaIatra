package medic.iatro.api.domain.addressData;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
    private String city;
    private String street;

    public Address() {
        // Constructor sin argumentos requerido por Hibernate
    }

    public Address(DataAddress address) {
        this.city = address.city();
        this.street = address.street();
    }

}
