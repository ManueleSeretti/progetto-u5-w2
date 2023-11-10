package ManueleSeretti.progettou5w2.Repositories;

import ManueleSeretti.progettou5w2.Entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
