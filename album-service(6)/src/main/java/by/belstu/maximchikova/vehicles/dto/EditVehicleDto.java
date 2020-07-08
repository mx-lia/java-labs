package by.belstu.maximchikova.vehicles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditVehicleDto {

    @NotNull(message = "{validation.vehicle.type.null}")
    @Size(max = 32, message = "{validation.vehicle.type.size}")
    private String type;

    @Size(max = 200, message = "{validation.vehicle.model.size}")
    private String model;
}
