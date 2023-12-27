package app.saving.facade;

import app.saving.entity.UserEntity;
import app.saving.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;

    public void create(String username){
        UserEntity userEntity = new UserEntity().setId(UUID.randomUUID()).setUsername(username);
        userRepository.save(userEntity);
    }


}
