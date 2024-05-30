package TodoListforGDSC.demo.authen.service;

import TodoListforGDSC.demo.authen.entity.UserEntity;
import TodoListforGDSC.demo.authen.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepositoryInterface userRepositoryInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepositoryInterface.findByUsername(username);

        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            return userEntity;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
