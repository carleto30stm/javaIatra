package medic.iatro.api.domain.medico.repository;

import medic.iatro.api.domain.medico.model.MedicoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
    Page<MedicoModel> findByActiveTrue(Pageable pageable);
}
