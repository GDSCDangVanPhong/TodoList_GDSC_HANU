package TodoListforGDSC.demo.authen.service;

import TodoListforGDSC.demo.authen.controller.LogoutRequest;
import TodoListforGDSC.demo.authen.entity.JwtEntity;
import TodoListforGDSC.demo.authen.repository.JwtRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class tokenService {
    @Autowired
    private JwtRepositoryInterface jwtRepository;

    public void saveToken( String token){
        JwtEntity userToken = new JwtEntity();
        userToken.setJwt(token);
        jwtRepository.save(userToken);
    }
    public void deleteToken(Optional<JwtEntity> jwtEntity){

        if(jwtEntity.isPresent()){
            JwtEntity targetToken = jwtEntity.get();
            jwtRepository.delete(targetToken);
        }
    }
}
