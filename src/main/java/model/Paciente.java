package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
@Setter
public class Paciente {

    private long id;
    private String apellido;
    private String nombre;
    private int dni;
    private String domicilio;

    public long getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public LocalDate getAlta() {
        return alta;
    }

    private LocalDate alta;
}
