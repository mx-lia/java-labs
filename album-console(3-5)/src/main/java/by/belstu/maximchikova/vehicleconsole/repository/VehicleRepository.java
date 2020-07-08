package by.belstu.maximchikova.vehicleconsole.repository;

import by.belstu.maximchikova.vehicleconsole.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
