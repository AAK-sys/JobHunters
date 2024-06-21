package learn.resume_builder.domain;

import learn.resume_builder.data.AppUserRepository;
import learn.resume_builder.data.ExperienceRepository;
import learn.resume_builder.models.Experience;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class ExperienceService {
    private final ExperienceRepository repository;
    private final AppUserRepository userRepository;

    public ExperienceService(ExperienceRepository repository, AppUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Experience findById(int experienceId) {
        return repository.findById(experienceId);
    }

    public Result<Experience> add(Experience experience) {
        Result<Experience> result = new Result<>();
        if(experience == null) {
            result.addMessage("Experience can not be null.", ResultType.INVALID);
            return result;
        }

        result = validate(experience);

        if(experience.getExperienceId() != 0) {
            result.addMessage("experienceId can not be set for `add` operation.", ResultType.INVALID);
        }

        if(userRepository.findById(experience.getUserId()) == null) {
            result.addMessage("userId does not associate with an existing user.", ResultType.INVALID);
        }

        if(!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(experience));
        return result;
    }

    public Result<Experience> update(Experience experience) {
        Result<Experience> result = new Result<>();
        if(experience == null) {
            result.addMessage("Experience can not be null.", ResultType.INVALID);
            return result;
        }

        result = validate(experience);

        if(experience.getExperienceId() <= 0) {
            result.addMessage("experienceId must be set for `update` operation.", ResultType.INVALID);
        }

        if(userRepository.findById(experience.getUserId()) == null) {
            result.addMessage("userId does not associate with an existing user.", ResultType.INVALID);
        }

        if(!result.isSuccess()) {
            return result;
        }

        if(!repository.update(experience)) {
            String msg = String.format("experienceId: %s, not found.", experience.getExperienceId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int experienceId) {
        return repository.deleteById(experienceId);
    }

    public Result<Experience> validate(Experience experience) {
        Result<Experience> result = new Result<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Experience>> violations = validator.validate(experience);

        if(!violations.isEmpty()) {
            for(ConstraintViolation<Experience> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
        }
        return result;
    }
}
