package io.github.notaphplover.catanserver.user.port;

import org.springframework.stereotype.Service;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDb;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;

@Service
public class UserFindQueryToUserFindQueryDbPort implements IPort<UserFindQuery, UserFindQueryDb> {

    @Override
    public UserFindQueryDb transform(UserFindQuery input) {
        
        UserFindQueryDb queryDb = new UserFindQueryDb(input.getUsername());
        
        return queryDb;
    }
    
}