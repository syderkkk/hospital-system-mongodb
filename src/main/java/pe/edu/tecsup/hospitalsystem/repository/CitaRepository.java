package pe.edu.tecsup.hospitalsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.hospitalsystem.entity.Cita;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends MongoRepository<Cita, String> {

    // Buscar citas por paciente
    List<Cita> findByPacienteIdPaciente(String idPaciente);

    // Buscar citas por médico
    List<Cita> findByMedicoIdMedico(String idMedico);

    // Buscar citas por estado
    List<Cita> findByEstado(String estado);

    // Buscar citas por médico y fecha
    List<Cita> findByMedicoIdMedicoAndFecha(String idMedico, LocalDate fecha);

    // Buscar citas por paciente y estado
    List<Cita> findByPacienteIdPacienteAndEstado(String idPaciente, String estado);

    // Contar citas por médico en una fecha
    @Query(value = "{ 'medico.$id': ?0, 'fecha': ?1 }", count = true)
    Long countByMedicoAndFecha(String idMedico, LocalDate fecha);

    // Buscar por fecha
    List<Cita> findByFecha(LocalDate fecha);
}