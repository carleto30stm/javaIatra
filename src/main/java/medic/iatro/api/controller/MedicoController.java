package medic.iatro.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medic.iatro.api.domain.medico.DataMedico;
import medic.iatro.api.domain.medico.DataResponseMedico;
import medic.iatro.api.domain.medico.ListMedico;
import medic.iatro.api.domain.medico.UpdateMedico;
import medic.iatro.api.domain.medico.model.MedicoModel;
import medic.iatro.api.domain.medico.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    @Autowired
    private MedicoService service;
    @PostMapping
    public ResponseEntity<DataResponseMedico>  createNewMedic(@RequestBody @Valid DataMedico medico, UriComponentsBuilder uriComponentsBuilder){
        ResponseEntity<DataResponseMedico> responseEntity = service.create(new MedicoModel(medico), uriComponentsBuilder);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return responseEntity;
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    @Secured("ROLE_USER")
    public ResponseEntity<Page<ListMedico>> getMedicos(Pageable pageable){
        return ResponseEntity.ok(service.get(pageable));
    }
    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseMedico> editMedico(@RequestBody @Valid  UpdateMedico medico){
        return service.updateMedico(medico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DataResponseMedico> desactiveMedico(@PathVariable Long id){
        service.desactiveMedico(id);
        return  ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DataResponseMedico> getMedicoById(@PathVariable Long id){
        return service.getMedicoByid(id);
    }
}
