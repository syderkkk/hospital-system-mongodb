package pe.edu.tecsup.hospitalsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    private String idMedico;

    private String nombres;

    private String apellidos;

    @Indexed(unique = true)
    private String colegiatura;

    private String telefono;

    private String correo;

    private String estado; // activo, inactivo
}