package ManueleSeretti.progettou5w2.Runners;

import ManueleSeretti.progettou5w2.Payloads.newUserDTO;
import ManueleSeretti.progettou5w2.Services.UserService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.function.Supplier;

@Component
public class RunnerUsers implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.ITALY);
        Supplier<newUserDTO> userSupplier = () -> new newUserDTO(faker.name().firstName(), faker.name().lastName(), faker.name().username(), faker.internet().emailAddress());

//        for (int i = 0; i < 30; i++) {
//            userService.save(userSupplier.get());
//        }
    }
}
