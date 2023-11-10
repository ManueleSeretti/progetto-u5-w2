package ManueleSeretti.progettou5w2.Services;

import ManueleSeretti.progettou5w2.Entities.Device;
import ManueleSeretti.progettou5w2.Entities.User;
import ManueleSeretti.progettou5w2.Payloads.newDeviceDTO;
import ManueleSeretti.progettou5w2.Repositories.DeviceRepository;
import ManueleSeretti.progettou5w2.exceptions.NotFoundException;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private Cloudinary cloudinary;

    public Device save(newDeviceDTO body) throws IOException {

        User u = userService.findById(body.userID());
        Device d = new Device();
        d.setName(body.name());
        d.setStato(body.stato());
        d.setUserId(u);

        return deviceRepository.save(d);
    }

    public Page<Device> getDevices(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return deviceRepository.findAll(pageable);
    }

    public Device findById(long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        Device d = this.findById(id);
        deviceRepository.delete(d);
    }

    public Device findByIdAndUpdate(long id, newDeviceDTO body) {
        Device found = this.findById(id);
        User u = userService.findById(body.userID());
        found.setName(body.name());
        found.setStato(body.stato());
        found.setUserId(u);


        return deviceRepository.save(found);

    }

}



