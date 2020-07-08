package by.belstu.maximchikova.vehicles.dto;

import by.belstu.maximchikova.vehicles.validator.annotation.RouteExists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddVehicleDto {

    @NotNull(message = "{validation.vehicle.type.null}")
    @Size(max = 32, message = "{validation.vehicle.type.size}")
    private String type;

    @NotNull(message = "{validation.vehicle.route.null}")
    @RouteExists(message = "{validation.vehicle.route.exists}")
    private String route;

    @NotNull(message = "{validation.vehicle.year.null}")
    private Integer releaseYear;

    @Size(max = 200, message = "{validation.vehicle.model.size}")
    private String model;
}
