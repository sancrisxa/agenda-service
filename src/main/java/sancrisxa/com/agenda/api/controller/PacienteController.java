package sancrisxa.com.agenda.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sancrisxa.com.agenda.api.mapper.PacienteMapper;
import sancrisxa.com.agenda.api.request.PacienteRequest;
import sancrisxa.com.agenda.api.response.PacienteResponse;
import sancrisxa.com.agenda.domain.entity.Paciente;
import sancrisxa.com.agenda.service.PacienteService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    private final PacienteMapper pacienteMapper;

    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest request) {


        Paciente paciente = pacienteMapper.toPaciente(request);

        Paciente pacienteSalvo = pacienteService.salvar(paciente);

        PacienteResponse pacienteResponse = pacienteMapper.toPacienteResponse(pacienteSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        List<Paciente> pacientes = pacienteService.listarTodos();

        return ResponseEntity.status(HttpStatus.OK).body(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {

        Optional<Paciente> optionalPaciente = this.pacienteService.buscarPorId(id);

        if (optionalPaciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Paciente paciente = optionalPaciente.get();

        return ResponseEntity.status(HttpStatus.OK).body(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> alterar(@PathVariable Long id, @RequestBody PacienteRequest pacienteRequest) {
        Paciente paciente = pacienteMapper.toPaciente(pacienteRequest);

        Paciente pacienteSalvo = pacienteService.alterar(id, paciente);

        PacienteResponse pacienteResponse = pacienteMapper.toPacienteResponse(pacienteSalvo);

        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable long id) {
        this.pacienteService.deletar(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
