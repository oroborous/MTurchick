package springxml.services;

import org.springframework.stereotype.Service;

@Service
public class CommentValidationService implements IValidationService {
    @Override
    public boolean ValidateString(String content) {
        return content.charAt(content.length() - 1) == '.';
    }
}
