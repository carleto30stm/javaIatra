package medic.iatro.api.domain.medico.service;

import medic.iatro.api.domain.addressData.DataAddress;
import medic.iatro.api.domain.medico.DataResponseMedico;
import medic.iatro.api.domain.medico.ListMedico;
import medic.iatro.api.domain.medico.UpdateMedico;
import medic.iatro.api.domain.medico.model.MedicoModel;
import medic.iatro.api.domain.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Service
public class MedicoService implements  IMedicoServise{
    @Autowired
    private MedicoRepository repository;

    @Override
    public ResponseEntity<DataResponseMedico> create(MedicoModel createMedico, UriComponentsBuilder uriComponentsBuilder) {
      MedicoModel medico = repository.save(createMedico);
      DataResponseMedico responseMedico = new DataResponseMedico(
              medico.getName(),
              medico.getEmail(),
              medico.getDni(),
              medico.getSpecialty().toString(),
              medico.getActive().toString(),
              new DataAddress(medico.getAddress().getCity(), medico.getAddress().getCity())

      );
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
      return  ResponseEntity.created(url).body(responseMedico);
    }

    @Override
    public Page<ListMedico> get(Pageable pageable) {
        return repository.findByActiveTrue(pageable).map(ListMedico:: new);
    }

    @Override
    public ResponseEntity<DataResponseMedico> updateMedico(UpdateMedico updateMedico) {
        MedicoModel medico = repository.getReferenceById(updateMedico.id());
        medico.updateData(updateMedico);
        return  ResponseEntity.ok(new DataResponseMedico(
                medico.getName(),
                medico.getEmail(),
                medico.getDni(),
                medico.getSpecialty().toString(),
                medico.getActive().toString(),
                new DataAddress(medico.getAddress().getCity(), medico.getAddress().getStreet())
        ));
    }

    @Override
    public void desactiveMedico(Long id) {
        MedicoModel medico = repository.getReferenceById(id);
        medico.desactive(medico);
    }

    @Override
    public ResponseEntity<DataResponseMedico> getMedicoByid(Long id) {
        MedicoModel medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataResponseMedico(
                medico.getName(),
                medico.getEmail(),
                medico.getDni(),
                medico.getSpecialty().toString(),
                medico.getActive().toString(),
                new DataAddress(medico.getAddress().getCity(), medico.getAddress().getStreet())
        ));
    }

}
