package by.belstu.maximchikova.vehicleconsole.service;

import by.belstu.maximchikova.vehicleconsole.dto.AddVehicleDto;
import by.belstu.maximchikova.vehicleconsole.dto.EditVehicleDto;
import by.belstu.maximchikova.vehicleconsole.entity.Vehicle;
import by.belstu.maximchikova.vehicleconsole.entity.Route;
import by.belstu.maximchikova.vehicleconsole.exception.VehicleNotFoundException;
import by.belstu.maximchikova.vehicleconsole.exception.RouteNotFoundException;
import by.belstu.maximchikova.vehicleconsole.repository.VehicleRepository;
import by.belstu.maximchikova.vehicleconsole.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    private RouteRepository routeRepository;

    private Logger logger = LoggerFactory.getLogger(VehicleService.class);

    public VehicleService(VehicleRepository vehicleRepository, RouteRepository routeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.routeRepository = routeRepository;
    }

    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle findVehicle(long vehicleId) {
        return vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Vehicle saveNewVehicle(AddVehicleDto vehicleDto) {
        logger.info("Saving new vehicle {}", vehicleDto);
        Vehicle vehicle = new Vehicle();
        Optional<Route> routeOptional = routeRepository.findRouteByName(vehicleDto.getRoute());
        vehicle.setRoute(routeOptional.orElseThrow(() -> new RouteNotFoundException("Route not found")));
        vehicle.setType(vehicleDto.getType());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setReleaseYear(vehicleDto.getReleaseYear());

        vehicleRepository.save(vehicle);
        logger.info("New vehicle has been saved. Generated id: {}", vehicle.getId());

        return vehicle;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Vehicle updateVehicle(long id, EditVehicleDto editVehicleDto) {
        Vehicle vehicle = findVehicle(id);
        vehicle.setType(editVehicleDto.getType());
        vehicle.setModel(editVehicleDto.getModel());

        vehicleRepository.save(vehicle);
        logger.info("Vehicle {} has been updated", vehicle.getId());

        return vehicle;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteVehicle(long id) {
        try {
            vehicleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new VehicleNotFoundException(e.getMessage(),e);
        }
        logger.info("Vehicle {} has been deleted", id);
    }
}
