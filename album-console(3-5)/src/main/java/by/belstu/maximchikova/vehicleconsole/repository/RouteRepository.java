package by.belstu.maximchikova.vehicleconsole.repository;

import by.belstu.maximchikova.vehicleconsole.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<Route> findRouteByName(String name);
}
