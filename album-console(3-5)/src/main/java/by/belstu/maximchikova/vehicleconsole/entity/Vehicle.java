package by.belstu.maximchikova.vehicleconsole.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{validation.vehicle.type.null}")
    @Size(max = 32, message = "{validation.vehicle.type.size}")
    private String type;

    @Size(max = 32, message = "{validation.vehicle.model.size}")
    private String model;

    @NotNull(message = "{validation.vehicle.release-year.null}")
    private int releaseYear;

    @ManyToOne(optional = false)
    @JoinColumn(name = "routeId")
    @NotNull
    private Route route;
}