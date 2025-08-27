package vn.edu.fpt.shopapp.services;

import org.springframework.stereotype.Service;
import vn.edu.fpt.shopapp.dto.UserDTO;
import vn.edu.fpt.shopapp.exceptions.DataNotFoundException;
import vn.edu.fpt.shopapp.models.User;

@Service
public interface IUserService {
    User createUser(UserDTO user) throws DataNotFoundException;

    String login(String phoneNumber, String password);
}
