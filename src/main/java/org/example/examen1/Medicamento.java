package org.example.examen1;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "Medicamento")
public class Medicamento {

    @Id
    @Size(max = 20)
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Size(max = 30)
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "plan")
    private Integer plan;
}