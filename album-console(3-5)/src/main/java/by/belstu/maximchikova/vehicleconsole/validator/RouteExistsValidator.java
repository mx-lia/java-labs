package by.belstu.maximchikova.vehicleconsole.validator;

import by.belstu.maximchikova.vehicleconsole.entity.Route;
import by.belstu.maximchikova.vehicleconsole.repository.RouteRepository;
import by.belstu.maximchikova.vehicleconsole.validator.annotation.RouteExists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class RouteExistsValidator implements ConstraintValidator<RouteExists, String> {

    private RouteRepository routeRepository;

    public RouteExistsValidator(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public void initialize(RouteExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Route> routeOptional = routeRepository.findRouteByName(name);
        return routeOptional.isPresent();
    }
}
