package org.example.examen1;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 100)
    @Column(name = "clave", nullable = false, length = 100)
    private String clave;

    @Size(max = 10)
    @Column(name = "rol", nullable = false, length = 10)
    private String rol;
}