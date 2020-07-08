package by.belstu.maximchikova.vehicleconsole.controller;

import by.belstu.maximchikova.vehicleconsole.dto.AddVehicleDto;
import by.belstu.maximchikova.vehicleconsole.dto.EditVehicleDto;
import by.belstu.maximchikova.vehicleconsole.entity.Vehicle;
import by.belstu.maximchikova.vehicleconsole.service.VehicleService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @InitBinder
    public void allowEmptyDateBinding(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    public ModelAndView showAllVehicles() {
        List<Vehicle> vehicles = vehicleService.findAllVehicles();
        return new ModelAndView("vehicles", "vehicles", vehicles);
    }

    @GetMapping("/create")
    public ModelAndView showVehicleForm() {
        return new ModelAndView("vehicle-add-form", "vehicleDto", new AddVehicleDto());
    }

    @PostMapping
    public ModelAndView createVehicle(@Valid @ModelAttribute("vehicleDto") AddVehicleDto vehicleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("vehicle-add-form", "vehicleDto", vehicleDto);
        }

        vehicleService.saveNewVehicle(vehicleDto);

        return new ModelAndView("redirect:/vehicles");
    }

    @GetMapping("/{vehicleId}/edit")
    public ModelAndView showEditForm(@PathVariable long vehicleId) {
        Vehicle vehicle = vehicleService.findVehicle(vehicleId);

        return new ModelAndView("vehicle-edit-form", "editVehicleDto", new EditVehicleDto(vehicleId, vehicle.getType(), vehicle.getModel()));
    }

    @PostMapping("/{vehicleId}")
    public String updateProfile(@PathVariable long vehicleId, @Valid @ModelAttribute EditVehicleDto editVehicleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return String.format("redirect:/vehicles/%d/edit", vehicleId);
        }
        vehicleService.updateVehicle(vehicleId, editVehicleDto);

        return "redirect:/vehicles";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{vehicleId}")
    public void deleteProfile(@PathVariable Long vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
    }
}
