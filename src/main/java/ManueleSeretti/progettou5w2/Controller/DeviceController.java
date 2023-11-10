package ManueleSeretti.progettou5w2.Controller;

import ManueleSeretti.progettou5w2.Entities.Device;
import ManueleSeretti.progettou5w2.Payloads.newDeviceDTO;
import ManueleSeretti.progettou5w2.Services.DeviceService;
import ManueleSeretti.progettou5w2.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("")
    public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return deviceService.getDevices(page, size);
    }

    // 2. POST http://localhost:3001/users (+ body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Device saveUser(@RequestBody @Validated newDeviceDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return deviceService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // 3. GET http://localhost:3001/users/:id
    @GetMapping("/{id}")
    public Device findById(@PathVariable int id) {
        return deviceService.findById(id);
    }

    // 4. PUT http://localhost:3001/users/:id (+ body)
    @PutMapping("/{id}")
    public Device
    findByIdAndUpdate(@PathVariable int id, @RequestBody newDeviceDTO
            body) {
        return deviceService.findByIdAndUpdate(id, body);
    }

    // 5. DELETE http://localhost:3001/users/:id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable long id) {
        deviceService.findByIdAndDelete(id);
    }

}
