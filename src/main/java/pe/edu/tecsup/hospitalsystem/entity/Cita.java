package pe.edu.tecsup.hospitalsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "cita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    private String idCita;

    @DBRef
    private Paciente paciente;

    @DBRef
    private Medico medico;

    private LocalDate fecha;

    private LocalTime hora;

    private String motivo;

    private String estado; // programada, atendida, cancelada
}