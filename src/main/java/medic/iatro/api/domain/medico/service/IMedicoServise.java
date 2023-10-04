package medic.iatro.api.domain.medico.service;

import medic.iatro.api.domain.medico.DataResponseMedico;
import medic.iatro.api.domain.medico.ListMedico;
import medic.iatro.api.domain.medico.UpdateMedico;
import medic.iatro.api.domain.medico.model.MedicoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface IMedicoServise {
    ResponseEntity<DataResponseMedico> create(MedicoModel medico, UriComponentsBuilder uriComponentsBuilder);
    Page<ListMedico> get(Pageable pageable);

    ResponseEntity updateMedico(UpdateMedico updateMedico);

    void desactiveMedico(Long id);

    ResponseEntity<DataResponseMedico> getMedicoByid(Long id);
}
