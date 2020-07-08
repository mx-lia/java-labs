package by.belstu.maximchikova.vehicles.controller.api.v1;

import by.belstu.maximchikova.vehicles.dto.AddVehicleDto;
import by.belstu.maximchikova.vehicles.dto.EditVehicleDto;
import by.belstu.maximchikova.vehicles.entity.Vehicle;
import by.belstu.maximchikova.vehicles.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
@Tag(name = "vehicles", description = "the Vehicle API")
public class VehicleControllerV1 {

    private VehicleService vehicleService;

    public VehicleControllerV1(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Operation(summary = "Gets all vehicles", tags = {"vehicles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Vehicle.class))))})
    @GetMapping(produces = {"application/json", "application/xml"})
    public List<Vehicle> getAllVehicles() {
        return vehicleService.findAllVehicles();
    }

    @Operation(summary = "Finds an vehicle by id", tags = {"vehicles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")})
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public Vehicle getVehicleById(@PathVariable long id) {
        return vehicleService.findVehicle(id);
    }

    @Operation(summary = "Creates a new vehicle", tags = {"vehicles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle created",
                    content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = {"application/json", "application/xml"})
    public Vehicle createVehicle(@Valid @RequestBody AddVehicleDto vehicleDto) {
        return vehicleService.saveNewVehicle(vehicleDto);
    }

    @Operation(summary = "Updates an existing vehicle", tags = {"vehicles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle updated",
                    content = @Content(schema = @Schema(implementation = Vehicle.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PutMapping(value = "/{id}", consumes = {"application/json", "application/xml"})
    public Vehicle updateVehicle(@PathVariable long id, @Valid @RequestBody EditVehicleDto editVehicleDto) {
        return vehicleService.updateVehicle(id, editVehicleDto);
    }

    @Operation(summary = "Deletes an vehicle", tags = {"vehicles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable long id) {
        vehicleService.deleteVehicle(id);
    }
}
