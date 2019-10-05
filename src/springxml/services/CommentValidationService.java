package springxml.services;

import org.springframework.stereotype.Component;

@Component
public class CommentValidationService implements IValidationService {
    @Override
    public boolean ValidateString(String content) {
        return false;
    }
}
