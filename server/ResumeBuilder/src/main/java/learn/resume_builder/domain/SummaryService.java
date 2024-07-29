package learn.resume_builder.domain;

import learn.resume_builder.data.SummaryRepository;
import learn.resume_builder.data.UserRepository;
import learn.resume_builder.models.Summary;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class SummaryService {
    private final SummaryRepository repository;
    private final UserRepository userRepository;

    public SummaryService(SummaryRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Summary findById(int summaryId) {
        return repository.findById(summaryId);
    }

    public Result<Summary> add(Summary summary) {
        Result<Summary> result = new Result<>();
        if(summary == null) {
            result.addMessage("Summary can not be null.", ResultType.INVALID);
            return result;
        }

        result = validate(summary);

        if(summary.getSummaryId() != 0) {
            result.addMessage("summaryId can not be set for `add` operation.", ResultType.INVALID);
        }

        if(userRepository.findById(summary.getUserId()) == null) {
            result.addMessage("userId does not associate with an existing user.", ResultType.INVALID);
        }

        if(!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(summary));
        return result;
    }

    public Result<Summary> update(Summary summary) {
        Result<Summary> result = new Result<>();
        if (summary == null) {
            result.addMessage("UserInfo can not be null.", ResultType.INVALID);
            return result;
        }

        result = validate(summary);

        if (summary.getSummaryId() <= 0) {
            result.addMessage("summaryId must be set for `update` operation.", ResultType.INVALID);
        }

        if (userRepository.findById(summary.getUserId()) == null) {
            result.addMessage("userId does not associate with an existing user.", ResultType.INVALID);
        }

        if (!result.isSuccess()) {
            return result;
        }

        if (!repository.update(summary)) {
            String msg = String.format("summaryId: %s, not found.", summary.getSummaryId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int summaryId) {
        return repository.deleteById(summaryId);
    }

    private Result<Summary> validate(Summary summary) {
        Result<Summary> result = new Result<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Summary>> violations = validator.validate(summary);

        if(!violations.isEmpty()) {
            for(ConstraintViolation<Summary> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
        }
        return result;
    }
}
