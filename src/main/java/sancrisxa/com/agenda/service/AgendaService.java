package sancrisxa.com.agenda.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sancrisxa.com.agenda.domain.entity.Agenda;
import sancrisxa.com.agenda.domain.entity.Paciente;
import sancrisxa.com.agenda.domain.repository.AgendaRepository;
import sancrisxa.com.agenda.exception.BusinessException;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final PacienteService pacienteService;

    public List<Agenda> listarTodos() {
        return agendaRepository.findAll();
    }

    public Optional<Agenda> buscarPorId(Long id) {
        return agendaRepository.findById(id);
    }

    public Agenda salvar(Agenda agenda) {
        Optional<Paciente> optionalPaciente = pacienteService.buscarPorId(agenda.getId());

        if (optionalPaciente.isEmpty()) {
            throw new BusinessException("Paciente não encontrado");
        }

        Optional<Agenda> agendaByHorario = agendaRepository.findByHorario(agenda.getHorario());

        if (agendaByHorario.isPresent()) {
            throw new BusinessException("Já existe agendamento para esse horario");
        }

        agenda.setPaciente(optionalPaciente.get());
        agenda.setDataCriacao(LocalDateTime.now());

        return agendaRepository.save(agenda);
    }


}
