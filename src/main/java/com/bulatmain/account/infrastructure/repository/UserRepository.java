package com.bulatmain.account.infrastructure.repository;

import com.bulatmain.account.application.model.dto.UserDTO;
import com.bulatmain.account.application.port.gateway.UserGateway;
import com.bulatmain.account.infrastructure.repository.mapping.UserCollection;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile({ "prod", "dev" })
public interface UserRepository extends
        UserGateway,
        MongoRepository<UserCollection, String> {

    @Override
    default boolean exists(String id) {
        var dtoOpt = findById(id);
        return dtoOpt.isPresent();
    }

    @Override
    default String save(UserDTO userDTO) {
        var obj = save(UserCollection.fromDTO(userDTO));
        return obj.getId();
    }
}
