package pe.edu.tecsup.hospitalsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.hospitalsystem.entity.Paciente;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends MongoRepository<Paciente, String> {

    Optional<Paciente> findByDni(String dni);

    List<Paciente> findByEstado(String estado);

    List<Paciente> findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(
            String nombres, String apellidos
    );
}