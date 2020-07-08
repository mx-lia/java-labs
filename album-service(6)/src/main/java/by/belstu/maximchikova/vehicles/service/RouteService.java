package by.belstu.maximchikova.vehicles.service;

import by.belstu.maximchikova.vehicles.dto.RouteDto;
import by.belstu.maximchikova.vehicles.entity.Route;
import by.belstu.maximchikova.vehicles.exception.RouteNotFoundException;
import by.belstu.maximchikova.vehicles.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private Logger logger = LoggerFactory.getLogger(VehicleService.class);

    private RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> findAllRoutes() {
        return routeRepository.findAll();
    }

    public Route findRouteById(long id) {
        return routeRepository.findById(id).orElseThrow(() -> new RouteNotFoundException("Route not found"));
    }

    public Route saveNewRoute(RouteDto routeDto) {
        logger.info("Saving new route {}", routeDto);
        Route route = new Route();

        route.setName(routeDto.getName());
        route.setDescription(routeDto.getDescription());

        routeRepository.save(route);
        logger.info("New route has been saved. Generated id: {}", route.getId());

        return route;
    }

    public Route updateRoute(long id, RouteDto routeDto) {
        Route route = findRouteById(id);
        route.setName(routeDto.getName());
        route.setDescription(routeDto.getDescription());

        routeRepository.save(route);
        logger.info("Route {} has been updated", route.getId());

        return route;
    }

    public void deleteRoute(long id) {
        try {
            routeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RouteNotFoundException("Route not found", e);
        }
        logger.info("Route {} has been deleted", id);
    }
}
