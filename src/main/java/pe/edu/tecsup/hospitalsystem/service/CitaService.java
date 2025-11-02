package pe.edu.tecsup.hospitalsystem.service;

import pe.edu.tecsup.hospitalsystem.entity.Cita;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CitaService {

    List<Cita> listarTodasCitas();

    Optional<Cita> obtenerCitaPorId(String id);

    Cita guardarCita(Cita cita);

    Cita actualizarCita(String id, Cita citaActualizada);

    void eliminarCita(String id);

    List<Cita> listarCitasPorPaciente(String idPaciente);

    List<Cita> listarCitasPorMedico(String idMedico);

    List<Cita> listarCitasPorEstado(String estado);

    List<Cita> listarCitasPorFecha(LocalDate fecha);

    Cita reprogramarCita(String idCita, LocalDate nuevaFecha, String nuevaHora);

    Cita cancelarCita(String idCita);

    Cita marcarCitaComoAtendida(String idCita);

    boolean validarDisponibilidadMedico(String idMedico, LocalDate fecha, String hora);
}