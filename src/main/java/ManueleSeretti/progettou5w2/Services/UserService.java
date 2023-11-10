package ManueleSeretti.progettou5w2.Services;

import ManueleSeretti.progettou5w2.Entities.User;
import ManueleSeretti.progettou5w2.Payloads.newUserDTO;
import ManueleSeretti.progettou5w2.Repositories.UserRepository;
import ManueleSeretti.progettou5w2.exceptions.BadRequestException;
import ManueleSeretti.progettou5w2.exceptions.NotFoundException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserRepository utenteRepository;

    @Autowired
    private Cloudinary cloudinary;

    public User save(newUserDTO body) throws IOException {
        utenteRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });
        utenteRepository.findByUsername(body.username()).ifPresent(user -> {
            throw new BadRequestException("L'username " + body.username() + " è già utilizzata!");
        });

        User u = new User();
        u.setName(body.name());
        u.setSurname(body.surname());
        u.setUserName(body.username());
        u.setEmail(body.email());
        u.setImgUrl("http://ui-avatars.com/api/?name=" + u.getName() + "+" + u.getSurname());

        return utenteRepository.save(u);
    }

    public Page<User> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return utenteRepository.findAll(pageable);
    }

    public User findById(long id) {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        User u = this.findById(id);
        utenteRepository.delete(u);
    }

    public User findByIdAndUpdate(long id, User body) {
        User found = this.findById(id);

        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());


        return utenteRepository.save(found);

    }

    public String uploadPicture(long id, MultipartFile file) throws IOException {
        User u = this.findById(id);
        String imgUrl = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        u.setImgUrl(imgUrl);
        utenteRepository.save(u);
        return imgUrl;
    }
}
