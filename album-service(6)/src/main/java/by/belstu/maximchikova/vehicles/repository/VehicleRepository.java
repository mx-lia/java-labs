package by.belstu.maximchikova.vehicles.repository;

import by.belstu.maximchikova.vehicles.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
