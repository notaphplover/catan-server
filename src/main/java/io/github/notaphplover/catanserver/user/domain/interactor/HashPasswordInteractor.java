package io.github.notaphplover.catanserver.user.domain.interactor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import io.github.notaphplover.catanserver.common.domain.interactor.IInteractor;
import io.github.notaphplover.catanserver.common.exception.UnableToPortException;

@Service
public class HashPasswordInteractor implements IInteractor<String, String> {

    @Override
    public String interact(String input) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new UnableToPortException(e);
        }
        
        byte[] encodedhash = digest.digest(
            input.getBytes(StandardCharsets.UTF_8));

        return new String(encodedhash);
    }    
}
