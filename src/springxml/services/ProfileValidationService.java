package springxml.services;

import org.springframework.stereotype.Service;

@Service
public class ProfileValidationService implements IValidationService {
    @Override
    public boolean ValidateString(String content) {
        return false;
    }
}
