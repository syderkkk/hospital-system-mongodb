package pe.edu.tecsup.hospitalsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    private String idPaciente;

    @Indexed(unique = true)
    private String dni;

    private String nombres;

    private String apellidos;

    private LocalDate fechaNacimiento;

    private String sexo; // M o F

    private String direccion;

    private String telefono;

    private String correo;

    private String estado; // activo, inactivo
}