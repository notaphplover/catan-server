package io.github.notaphplover.catanserver.user.adapter.api.reqHandler;

import io.github.notaphplover.catanserver.common.adapter.api.reqHandler.IReqHandler;
import io.github.notaphplover.catanserver.user.adapter.api.model.UserApi;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;

public class PostUserReqHandler implements IReqHandler<PostUserRequest, UserApi> {

    @Override
    public UserApi handle(PostUserRequest request) {
        return null;
    }

}
