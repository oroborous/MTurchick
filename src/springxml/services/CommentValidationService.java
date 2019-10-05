package springxml.services;

import org.springframework.stereotype.Service;

@Service
public class CommentValidationService implements IValidationService {
    @Override
    public String GiveValidationExplanation() {
        return "Check string for a period at the end.";
    }

    @Override
    public boolean ValidateString(String content) {
        return content.charAt(content.length() - 1) == '.';
    }
}
