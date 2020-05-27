package io.github.notaphplover.catanserver.user.adapter.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserCreationQueryDb;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.User;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import io.github.notaphplover.catanserver.user.port.UserCreationQueryToUserCreationQueryDbPort;

@Service
public class UserRepositoryManager {
    
    private CrudRepository<UserDb, Long> innerRepository;

    private UserCreationQueryToUserCreationQueryDbPort userCreationQueryToUserCreationQueryDbPort;

    public UserRepositoryManager(CrudRepository<UserDb, Long> innerRepository, UserCreationQueryToUserCreationQueryDbPort userCreationQueryToUserCreationQueryDbPort) {
        this.innerRepository = innerRepository;
        this.userCreationQueryToUserCreationQueryDbPort = userCreationQueryToUserCreationQueryDbPort;
    }

    public IUser create(UserCreationQuery query) {
        UserCreationQueryDb queryDb = userCreationQueryToUserCreationQueryDbPort.transform(query);
        
        UserDb userDb = getUserDbFromUserCreationQueryDb(queryDb);

        UserDb userCreated = this.innerRepository.save(userDb);

        return userDbToUser(userCreated);
    }

    private UserDb getUserDbFromUserCreationQueryDb(UserCreationQueryDb queryDb) {
        UserDb userDb = new UserDb();
        userDb.setUsername(queryDb.getUsername());
        userDb.setPasswordHash(queryDb.getPasswordHash());

        return userDb;
    }

    private IUser userDbToUser(UserDb userDb) {
        IUser user = new User(userDb.getId());
        user.setPasswordHash(userDb.getPasswordHash());
        user.setUsername(userDb.getUsername());

        return user;
    }
}