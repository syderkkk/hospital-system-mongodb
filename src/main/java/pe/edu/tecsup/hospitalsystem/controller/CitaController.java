package pe.edu.tecsup.hospitalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.hospitalsystem.entity.Cita;
import pe.edu.tecsup.hospitalsystem.service.CitaService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> listarTodas() {
        return ResponseEntity.ok(citaService.listarTodasCitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable String id) {
        return citaService.obtenerCitaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cita cita) {
        try {
            Cita nuevaCita = citaService.guardarCita(cita);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCita);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody Cita cita) {
        try {
            Cita citaActualizada = citaService.actualizarCita(id, cita);
            return ResponseEntity.ok(citaActualizada);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        citaService.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<Cita>> listarPorPaciente(@PathVariable String idPaciente) {
        return ResponseEntity.ok(citaService.listarCitasPorPaciente(idPaciente));
    }

    @GetMapping("/medico/{idMedico}")
    public ResponseEntity<List<Cita>> listarPorMedico(@PathVariable String idMedico) {
        return ResponseEntity.ok(citaService.listarCitasPorMedico(idMedico));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Cita>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(citaService.listarCitasPorEstado(estado));
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Cita>> listarPorFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha
    ) {
        return ResponseEntity.ok(citaService.listarCitasPorFecha(fecha));
    }

    @PatchMapping("/{id}/reprogramar")
    public ResponseEntity<?> reprogramar(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam String hora
    ) {
        try {
            Cita citaReprogramada = citaService.reprogramarCita(id, fecha, hora);
            return ResponseEntity.ok(citaReprogramada);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable String id) {
        try {
            Cita citaCancelada = citaService.cancelarCita(id);
            return ResponseEntity.ok(citaCancelada);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PatchMapping("/{id}/atender")
    public ResponseEntity<?> marcarAtendida(@PathVariable String id) {
        try {
            Cita citaAtendida = citaService.marcarCitaComoAtendida(id);
            return ResponseEntity.ok(citaAtendida);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping("/validar-disponibilidad")
    public ResponseEntity<Map<String, Boolean>> validarDisponibilidad(
            @RequestParam String idMedico,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam String hora
    ) {
        boolean disponible = citaService.validarDisponibilidadMedico(idMedico, fecha, hora);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("disponible", disponible);
        return ResponseEntity.ok(respuesta);
    }
}