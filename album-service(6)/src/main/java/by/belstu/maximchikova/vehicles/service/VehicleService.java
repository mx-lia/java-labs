package by.belstu.maximchikova.vehicles.service;

import by.belstu.maximchikova.vehicles.dto.AddVehicleDto;
import by.belstu.maximchikova.vehicles.dto.EditVehicleDto;
import by.belstu.maximchikova.vehicles.entity.Vehicle;
import by.belstu.maximchikova.vehicles.entity.Route;
import by.belstu.maximchikova.vehicles.exception.VehicleNotFoundException;
import by.belstu.maximchikova.vehicles.exception.VehicleValidationException;
import by.belstu.maximchikova.vehicles.repository.VehicleRepository;
import by.belstu.maximchikova.vehicles.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Year;
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

    public Vehicle saveNewVehicle(AddVehicleDto vehicleDto) {
        logger.info("Saving new vehicle {}", vehicleDto);
        Vehicle vehicle = new Vehicle();
        Optional<Route> artistOptional = routeRepository.findRouteByName(vehicleDto.getRoute());
        vehicle.setRoute(artistOptional.orElseThrow(() -> new VehicleValidationException("Route not found")));
        vehicle.setType(vehicleDto.getType());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setReleaseYear(vehicleDto.getReleaseYear());

        vehicleRepository.save(vehicle);
        logger.info("New vehicle has been saved. Generated id: {}", vehicle.getId());

        return vehicle;
    }

    public Vehicle updateVehicle(long id, EditVehicleDto editVehicleDto) {
        Vehicle vehicle = findVehicle(id);
        vehicle.setType(editVehicleDto.getType());
        vehicle.setModel(editVehicleDto.getModel());

        vehicleRepository.save(vehicle);
        logger.info("Vehicle {} has been updated", vehicle.getId());

        return vehicle;
    }

    public void deleteVehicle(long id) {
        try {
            vehicleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new VehicleNotFoundException("Vehicle not found", e);
        }
        logger.info("Vehicle {} has been deleted", id);
    }
}
