package springxml.services;

import org.springframework.stereotype.Service;

@Service
public class ProfileValidationService implements IValidationService {
    @Override
    public String GiveValidationExplanation() {
        return "Checks the content string for spaces.";
    }

    @Override
    public boolean ValidateString(String content) {
        return !content.contains(" ");
    }
}
