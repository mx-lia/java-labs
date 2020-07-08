package by.belstu.maximchikova.vehicles.controller.api.v1;

import by.belstu.maximchikova.vehicles.dto.RouteDto;
import by.belstu.maximchikova.vehicles.entity.Route;
import by.belstu.maximchikova.vehicles.service.RouteService;
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
@RequestMapping("api/v1/routes")
@Tag(name = "routes", description = "the Route API")
public class RouteControllerV1 {

    private RouteService routeService;

    public RouteControllerV1(RouteService routeService) {
        this.routeService = routeService;
    }

    @Operation(summary = "Gets all routes", tags = {"routes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Route.class))))})
    @GetMapping(produces = {"application/json", "application/xml"})
    public List<Route> getAllRoutes() {
        return routeService.findAllRoutes();
    }

    @Operation(summary = "Finds an route by id", tags = {"routes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Route.class))),
            @ApiResponse(responseCode = "404", description = "Route not found")})
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public Route getRouteById(@PathVariable long id) {
        return routeService.findRouteById(id);
    }

    @Operation(summary = "Creates a new route", tags = {"routes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle created",
                    content = @Content(schema = @Schema(implementation = Route.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = {"application/json", "application/xml"})
    public Route createRoute(@Valid @RequestBody RouteDto routeDto) {
        return routeService.saveNewRoute(routeDto);
    }

    @Operation(summary = "Updates an existing route", tags = {"routes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Route updated",
                    content = @Content(schema = @Schema(implementation = Route.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")})
    @PutMapping(value = "/{id}", consumes = {"application/json", "application/xml"})
    public Route updateRoute(@PathVariable long id, @Valid @RequestBody RouteDto routeDto) {
        return routeService.updateRoute(id, routeDto);
    }

    @Operation(summary = "Deletes an route", tags = {"routes"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Route not found")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable long id) {
        routeService.deleteRoute(id);
    }
}
