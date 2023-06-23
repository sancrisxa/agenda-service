package sancrisxa.com.agenda.api.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sancrisxa.com.agenda.api.request.PacienteRequest;
import sancrisxa.com.agenda.api.response.PacienteResponse;
import sancrisxa.com.agenda.domain.entity.Paciente;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper modelMapper;
    public Paciente toPaciente(PacienteRequest request) {

        return modelMapper.map(request, Paciente.class);
    }

    public PacienteResponse toPacienteResponse(Paciente paciente) {

        return modelMapper.map(paciente, PacienteResponse.class);
    }

}
