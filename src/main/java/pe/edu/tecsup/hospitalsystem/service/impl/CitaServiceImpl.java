package pe.edu.tecsup.hospitalsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.tecsup.hospitalsystem.entity.Cita;
import pe.edu.tecsup.hospitalsystem.entity.Medico;
import pe.edu.tecsup.hospitalsystem.entity.Paciente;
import pe.edu.tecsup.hospitalsystem.repository.CitaRepository;
import pe.edu.tecsup.hospitalsystem.repository.MedicoRepository;
import pe.edu.tecsup.hospitalsystem.repository.PacienteRepository;
import pe.edu.tecsup.hospitalsystem.service.CitaService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public List<Cita> listarTodasCitas() {
        return citaRepository.findAll();
    }

    @Override
    public Optional<Cita> obtenerCitaPorId(String id) {
        return citaRepository.findById(id);
    }

    @Override
    public Cita guardarCita(Cita cita) {
        // Validar que el paciente existe y está activo
        Paciente paciente = pacienteRepository.findById(cita.getPaciente().getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if (!"activo".equals(paciente.getEstado())) {
            throw new RuntimeException("El paciente no está activo");
        }

        // Validar que el médico existe y está activo
        Medico medico = medicoRepository.findById(cita.getMedico().getIdMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        if (!"activo".equals(medico.getEstado())) {
            throw new RuntimeException("El médico no está activo");
        }

        // Establecer estado inicial
        if (cita.getEstado() == null || cita.getEstado().isEmpty()) {
            cita.setEstado("programada");
        }

        return citaRepository.save(cita);
    }

    @Override
    public Cita actualizarCita(String id, Cita citaActualizada) {
        Cita citaExistente = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        citaExistente.setFecha(citaActualizada.getFecha());
        citaExistente.setHora(citaActualizada.getHora());
        citaExistente.setMotivo(citaActualizada.getMotivo());
        citaExistente.setEstado(citaActualizada.getEstado());

        if (citaActualizada.getPaciente() != null) {
            citaExistente.setPaciente(citaActualizada.getPaciente());
        }

        if (citaActualizada.getMedico() != null) {
            citaExistente.setMedico(citaActualizada.getMedico());
        }

        return citaRepository.save(citaExistente);
    }

    @Override
    public void eliminarCita(String id) {
        citaRepository.deleteById(id);
    }

    @Override
    public List<Cita> listarCitasPorPaciente(String idPaciente) {
        return citaRepository.findByPacienteIdPaciente(idPaciente);
    }

    @Override
    public List<Cita> listarCitasPorMedico(String idMedico) {
        return citaRepository.findByMedicoIdMedico(idMedico);
    }

    @Override
    public List<Cita> listarCitasPorEstado(String estado) {
        return citaRepository.findByEstado(estado);
    }

    @Override
    public List<Cita> listarCitasPorFecha(LocalDate fecha) {
        return citaRepository.findByFecha(fecha);
    }

    @Override
    public Cita reprogramarCita(String idCita, LocalDate nuevaFecha, String nuevaHora) {
        Cita cita = citaRepository.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if ("cancelada".equals(cita.getEstado())) {
            throw new RuntimeException("No se puede reprogramar una cita cancelada");
        }

        if ("atendida".equals(cita.getEstado())) {
            throw new RuntimeException("No se puede reprogramar una cita ya atendida");
        }

        cita.setFecha(nuevaFecha);
        cita.setHora(LocalTime.parse(nuevaHora));
        cita.setEstado("programada");

        return citaRepository.save(cita);
    }

    @Override
    public Cita cancelarCita(String idCita) {
        Cita cita = citaRepository.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if ("atendida".equals(cita.getEstado())) {
            throw new RuntimeException("No se puede cancelar una cita ya atendida");
        }

        cita.setEstado("cancelada");
        return citaRepository.save(cita);
    }

    @Override
    public Cita marcarCitaComoAtendida(String idCita) {
        Cita cita = citaRepository.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if ("cancelada".equals(cita.getEstado())) {
            throw new RuntimeException("No se puede marcar como atendida una cita cancelada");
        }

        cita.setEstado("atendida");
        return citaRepository.save(cita);
    }

    @Override
    public boolean validarDisponibilidadMedico(String idMedico, LocalDate fecha, String hora) {
        List<Cita> citasMedico = citaRepository.findByMedicoIdMedicoAndFecha(idMedico, fecha);

        LocalTime horaConsulta = LocalTime.parse(hora);

        for (Cita cita : citasMedico) {
            if (!"cancelada".equals(cita.getEstado()) && cita.getHora().equals(horaConsulta)) {
                return false;
            }
        }

        return true;
    }
}