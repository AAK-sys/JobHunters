package learn.resume_builder.domain;

import learn.resume_builder.data.UserRepository;
import learn.resume_builder.models.User;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService (UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(int userId) {
        return repository.findById(userId);
    }

    public Result<User> add(User user) {
        Result<User> result = new Result<>();
        if(user == null) {
            result.addMessage("User can not be null.", ResultType.INVALID);
            return result;
        }

         result = validate(user);

        if(user.getUserId() != 0) {
            result.addMessage("userId can not be set for `add` operation", ResultType.INVALID);
        }

        if(!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(user));
        return result;
    }

    public Result<User> update(User user) {
        Result<User> result = new Result<>();
        if(user == null) {
            result.addMessage("User can not be null.", ResultType.INVALID);
            return result;
        }

        result = validate(user);
        if(!result.isSuccess()) {
            return result;
        }

        if(user.getUserId() <= 0) {
            result.addMessage("userId must be set for `update` operation.", ResultType.INVALID);
            return result;
        }

        if(!repository.update(user)) {
            String msg = String.format("userId: %s, not found.", user.getUserId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int userId) {
        return repository.deleteById(userId);
    }

    public Result<User> validate(User user) {
        Result<User> result = new Result<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if(!violations.isEmpty()) {
            for(ConstraintViolation<User> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
        }
        return result;
    }
}
