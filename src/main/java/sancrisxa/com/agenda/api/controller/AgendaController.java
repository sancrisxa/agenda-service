package sancrisxa.com.agenda.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sancrisxa.com.agenda.api.mapper.AgendaMapper;
import sancrisxa.com.agenda.api.response.AgendaResponse;
import sancrisxa.com.agenda.domain.entity.Agenda;
import sancrisxa.com.agenda.service.AgendaService;
import sancrisxa.com.agenda.api.request.AgendaRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaService;
    private final AgendaMapper agendaMapper;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> buscarTodos() {
        List<Agenda> agendas = agendaService.listarTodos();
        List<AgendaResponse> agendaResponses = agendaMapper.toAgendaResponseList(agendas);
        return ResponseEntity.status(HttpStatus.OK).body(agendaResponses);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscarPorId(@PathVariable Long id) {
        Optional<Agenda> agendaOptional = agendaService.buscarPorId(id);

        if (agendaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AgendaResponse agendaResponse = agendaMapper.toAgendaResponse(agendaOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);
    }

    @PostMapping
    public ResponseEntity<AgendaResponse> salvar(@Valid @RequestBody AgendaRequest agendaRequest) {
        Agenda agenda = agendaMapper.toAgenda(agendaRequest);
        Agenda agendaSalva = agendaService.salvar(agenda);

        AgendaResponse agendaResponse = agendaMapper.toAgendaResponse(agendaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);
    }
}
