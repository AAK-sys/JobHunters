package learn.resume_builder.domain;

import learn.resume_builder.data.AppUserRepository;
import learn.resume_builder.data.EducationRepository;
import learn.resume_builder.data.UserRepository;
import learn.resume_builder.models.Education;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class EducationService {
    private final EducationRepository repository;
    private final AppUserRepository userRepository;

    public EducationService(EducationRepository repository, AppUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Education findById(int educationId) {
        return repository.findById(educationId);
    }

    public Result<Education> add(Education education) {
        Result<Education> result = new Result<>();
        if(education == null) {
            result.addMessage("UserInfo can not be null.", ResultType.INVALID);
            return result;
        }

        result = validate(education);

        if(education.getEducationId() != 0) {
            result.addMessage("educationId can not be set for `add` operation.", ResultType.INVALID);
        }

        if(userRepository.findById(education.getUserId()) == null) {
            result.addMessage("userId does not associate with an existing user.", ResultType.INVALID);
        }

        if(!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(education));
        return result;
    }

    public Result<Education> update(Education education) {
        Result<Education> result = new Result<>();
        if(education == null) {
            result.addMessage("UserInfo can not be null.", ResultType.INVALID);
            return result;
        }

        result = validate(education);

        if(education.getEducationId() <= 0) {
            result.addMessage("educationId must be set for `add` operation.", ResultType.INVALID);
        }

        if(userRepository.findById(education.getUserId()) == null) {
            result.addMessage("userId does not associate with an existing user.", ResultType.INVALID);
        }

        if(!result.isSuccess()) {
            return result;
        }

        if(!repository.update(education)) {
            String msg = String.format("educationId: %s, not found.", education.getEducationId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int educationId) {
        return repository.deleteById(educationId);
    }

    public Result<Education> validate(Education education) {
        Result<Education> result = new Result<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Education>> violations = validator.validate(education);

        if(!violations.isEmpty()) {
            for(ConstraintViolation<Education> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
        }
        return result;
    }
}
