package io.github.notaphplover.catanserver.user.port;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.github.notaphplover.catanserver.common.exception.UnableToPortException;
import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.User;

public class UserApiToUserPort implements IPort<IUserApi, IUser> {

    @Override
    public IUser transform(IUserApi input) {

        IUser user = new User();

        user.setUsername(input.getUsername());
        user.setPasswordHash(hashPassword(input.getPassword()));

        return user;
    }

    private String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new UnableToPortException(e);
        }
        
        byte[] encodedhash = digest.digest(
            password.getBytes(StandardCharsets.UTF_8));

        return new String(encodedhash);
    }

}
