package sancrisxa.com.agenda.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sancrisxa.com.agenda.exception.BusinessException;
import sancrisxa.com.agenda.domain.entity.Paciente;
import sancrisxa.com.agenda.domain.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {

        boolean existeCpf = false;

        Optional<Paciente> optionalPaciente = repository.findByCpf(paciente.getCpf());

        if (optionalPaciente.isPresent()) {
            if (!optionalPaciente.get().getId().equals(paciente.getId())) {
                existeCpf = true;
            }
        }

        if (existeCpf) {
            throw new BusinessException("Cpf já cadastrado");
        }

        return repository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Paciente alterar(Long id, Paciente paciente) {
        Optional<Paciente> optionalPaciente = this.buscarPorId(id);

        if (optionalPaciente.isEmpty()) {
            throw new BusinessException("Paciente não encontrado");
        }

        paciente.setId(id);

        return this.salvar(paciente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
