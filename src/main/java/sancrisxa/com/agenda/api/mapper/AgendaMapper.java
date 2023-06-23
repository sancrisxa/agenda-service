package sancrisxa.com.agenda.api.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sancrisxa.com.agenda.api.request.AgendaRequest;
import sancrisxa.com.agenda.api.request.PacienteRequest;
import sancrisxa.com.agenda.api.response.AgendaResponse;
import sancrisxa.com.agenda.api.response.PacienteResponse;
import sancrisxa.com.agenda.domain.entity.Agenda;
import sancrisxa.com.agenda.domain.entity.Paciente;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AgendaMapper {

    private final ModelMapper modelMapper;

    public Agenda toAgenda(AgendaRequest request) {

        return modelMapper.map(request, Agenda.class);
    }

    public AgendaResponse toAgendaResponse(Agenda agenda) {

        return modelMapper.map(agenda, AgendaResponse.class);
    }

    public List<AgendaResponse> toAgendaResponseList(List<Agenda> agendaList) {
        return agendaList.stream().map(this::toAgendaResponse).collect(Collectors.toList());
    }
}
