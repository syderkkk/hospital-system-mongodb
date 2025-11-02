package pe.edu.tecsup.hospitalsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.hospitalsystem.entity.Cita;
import pe.edu.tecsup.hospitalsystem.entity.Medico;
import pe.edu.tecsup.hospitalsystem.entity.Paciente;
import pe.edu.tecsup.hospitalsystem.repository.CitaRepository;
import pe.edu.tecsup.hospitalsystem.repository.MedicoRepository;
import pe.edu.tecsup.hospitalsystem.repository.PacienteRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Cargar datos solo si no existen
        if (pacienteRepository.count() == 0) {
            cargarPacientes();
        }

        if (medicoRepository.count() == 0) {
            cargarMedicos();
        }

        if (citaRepository.count() == 0) {
            //cargarCitas();
        }
    }

    private void cargarPacientes() {
        Paciente p1 = new Paciente();
        p1.setDni("12345678");
        p1.setNombres("Juan Carlos");
        p1.setApellidos("García López");
        p1.setFechaNacimiento(LocalDate.of(1985, 5, 15));
        p1.setSexo("M");
        p1.setDireccion("Av. Arequipa 1234, Lima");
        p1.setTelefono("987654321");
        p1.setCorreo("juan.garcia@email.com");
        p1.setEstado("activo");
        pacienteRepository.save(p1);

        Paciente p2 = new Paciente();
        p2.setDni("87654321");
        p2.setNombres("María Elena");
        p2.setApellidos("Rodríguez Pérez");
        p2.setFechaNacimiento(LocalDate.of(1990, 8, 22));
        p2.setSexo("F");
        p2.setDireccion("Jr. Lampa 567, Lima");
        p2.setTelefono("912345678");
        p2.setCorreo("maria.rodriguez@email.com");
        p2.setEstado("activo");
        pacienteRepository.save(p2);

        Paciente p3 = new Paciente();
        p3.setDni("45678912");
        p3.setNombres("Carlos Alberto");
        p3.setApellidos("Mendoza Torres");
        p3.setFechaNacimiento(LocalDate.of(1978, 3, 10));
        p3.setSexo("M");
        p3.setDireccion("Av. La Marina 890, Pueblo Libre");
        p3.setTelefono("998877665");
        p3.setCorreo("carlos.mendoza@email.com");
        p3.setEstado("activo");
        pacienteRepository.save(p3);
    }

    private void cargarMedicos() {
        Medico m1 = new Medico();
        m1.setNombres("Ana María");
        m1.setApellidos("Flores Castillo");
        m1.setColegiatura("CMP12345");
        m1.setTelefono("987123456");
        m1.setCorreo("ana.flores@hospital.com");
        m1.setEstado("activo");
        medicoRepository.save(m1);

        Medico m2 = new Medico();
        m2.setNombres("Roberto");
        m2.setApellidos("Sánchez Díaz");
        m2.setColegiatura("CMP67890");
        m2.setTelefono("987654123");
        m2.setCorreo("roberto.sanchez@hospital.com");
        m2.setEstado("activo");
        medicoRepository.save(m2);

        Medico m3 = new Medico();
        m3.setNombres("Patricia");
        m3.setApellidos("Vega Morales");
        m3.setColegiatura("CMP11223");
        m3.setTelefono("976543210");
        m3.setCorreo("patricia.vega@hospital.com");
        m3.setEstado("activo");
        medicoRepository.save(m3);
    }

    private void cargarCitas() {
        Paciente p1 = pacienteRepository.findByDni("12345678").orElse(null);
        Paciente p2 = pacienteRepository.findByDni("87654321").orElse(null);
        Paciente p3 = pacienteRepository.findByDni("45678912").orElse(null);

        Medico m1 = medicoRepository.findByColegiatura("CMP12345").orElse(null);
        Medico m2 = medicoRepository.findByColegiatura("CMP67890").orElse(null);
        Medico m3 = medicoRepository.findByColegiatura("CMP11223").orElse(null);

        Cita c1 = new Cita();
        c1.setPaciente(p1);
        c1.setMedico(m1);
        c1.setFecha(LocalDate.of(2025, 10, 20));
        c1.setHora(LocalTime.of(9, 0));
        c1.setMotivo("Consulta general");
        c1.setEstado("programada");

        Cita c2 = new Cita();
        c2.setPaciente(p2);
        c2.setMedico(m2);
        c2.setFecha(LocalDate.of(2025, 10, 21));
        c2.setHora(LocalTime.of(10, 30));
        c2.setMotivo("Dolor de cabeza");
        c2.setEstado("programada");

        Cita c3 = new Cita();
        c3.setPaciente(p3);
        c3.setMedico(m3);
        c3.setFecha(LocalDate.of(2025, 10, 22));
        c3.setHora(LocalTime.of(11, 15));
        c3.setMotivo("Chequeo anual");
        c3.setEstado("programada");

        Cita c4 = new Cita();
        c4.setPaciente(p1);
        c4.setMedico(m2);
        c4.setFecha(LocalDate.of(2025, 10, 23));
        c4.setHora(LocalTime.of(8, 45));
        c4.setMotivo("Revisión de análisis");
        c4.setEstado("atendida");

        Cita c5 = new Cita();
        c5.setPaciente(p2);
        c5.setMedico(m3);
        c5.setFecha(LocalDate.of(2025, 10, 24));
        c5.setHora(LocalTime.of(14, 0));
        c5.setMotivo("Consulta dermatológica");
        c5.setEstado("cancelada");

        citaRepository.save(c1);
        citaRepository.save(c2);
        citaRepository.save(c3);
        citaRepository.save(c4);
        citaRepository.save(c5);
    }
}
