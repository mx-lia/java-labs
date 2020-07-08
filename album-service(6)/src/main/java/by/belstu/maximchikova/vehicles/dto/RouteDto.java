package by.belstu.maximchikova.vehicles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {

    @Size(max = 200, message = "{validation.route.name.size}")
    @NotNull( message = "{validation.route.name.null}")
    private String name;

    @Size(max = 200, message = "{validation.route.description.size}")
    @NotNull( message = "{validation.route.description.null}")
    private String description;
}
