package vn.edu.fpt.shopapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import vn.edu.fpt.shopapp.dto.UserDTO;
import vn.edu.fpt.shopapp.exceptions.DataNotFoundException;
import vn.edu.fpt.shopapp.models.Role;
import vn.edu.fpt.shopapp.models.User;
import vn.edu.fpt.shopapp.repositories.RoleRepository;
import vn.edu.fpt.shopapp.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(UserDTO user) throws DataNotFoundException {
        String phoneNumber = user.getPhoneNumber();
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }

        User newUser = User
                .builder()
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .facebookAccountId(user.getFacebookAccountId())
                .googleAccountId(user.getGoogleAccountId())
                .dateOfBirth(user.getDateOfBirth())
                .password(user.getPassword())
                .build();
        Role role = roleRepository.findById(user.getRoleId()).orElseThrow(() -> new DataNotFoundException("Role not found"));
        newUser.setRole(role);
        if (user.getFacebookAccountId() == 0 && user.getGoogleAccountId() == 0) {
            String password = user.getPassword();
            //String endcodePassword =
        }
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) {
        return "";
    }
}
