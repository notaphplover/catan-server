package io.github.notaphplover.catanserver.user.adapter.api.reqHandler;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.notaphplover.catanserver.common.adapter.api.reqHandler.IReqHandler;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.request.GetUserRequest;
import io.github.notaphplover.catanserver.user.domain.interactor.FindUserInteractor;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;
import io.github.notaphplover.catanserver.user.port.UserToUserApiPort;

@Service
public class GetUserRequestHandler implements IReqHandler<GetUserRequest, Optional<IUserApi>> {

    private FindUserInteractor findUserInteractor;

    private UserToUserApiPort userToUserApiPort;

    public GetUserRequestHandler(FindUserInteractor findUserInteractor, UserToUserApiPort userToUserApiPort) {
        this.findUserInteractor = findUserInteractor;
        this.userToUserApiPort = userToUserApiPort;
    } 

    @Override
    public Optional<IUserApi> handle(GetUserRequest request) {
        
        UserFindQuery query = new UserFindQuery(request.getId(), request.getUsername());

        Optional<IUser> userFound = findUserInteractor.interact(query);

        return userFound.map((IUser u) -> userToUserApiPort.transform(u));
    }
    
}