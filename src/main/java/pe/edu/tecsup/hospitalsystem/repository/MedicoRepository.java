package pe.edu.tecsup.hospitalsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.hospitalsystem.entity.Medico;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends MongoRepository<Medico, String> {

    Optional<Medico> findByColegiatura(String colegiatura);

    List<Medico> findByEstado(String estado);

    List<Medico> findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(
            String nombres, String apellidos
    );
}