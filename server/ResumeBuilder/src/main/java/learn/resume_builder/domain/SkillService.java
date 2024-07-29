package learn.resume_builder.domain;

import learn.resume_builder.data.SkillRepository;
import learn.resume_builder.models.Skill;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class SkillService {
    private final SkillRepository repository;

    public SkillService(SkillRepository repository) {
        this.repository = repository;
    }

    public List<Skill> findAll() {
        return repository.findAll();
    }

    public Skill findById(int skillId) {
        return repository.findById(skillId);
    }

    public Result<Skill> add(Skill skill) {
        Result<Skill> result = new Result<>();
        if(skill == null) {
            result.addMessage("Skill can not be null.", ResultType.INVALID);
            return result;
        }

        result = validate(skill);

        if(skill.getSkillId() != 0) {
            result.addMessage("skillId can not be set for `add` operation.", ResultType.INVALID);
        }

        if(!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(skill));
        return result;
    }

    public Result<Skill> update(Skill skill) {
        Result<Skill> result = new Result<>();
        if(skill == null) {
            result.addMessage("Skill can not be null.", ResultType.INVALID);
            return result;
        }

        result = validate(skill);

        if(skill.getSkillId() <= 0) {
            result.addMessage("skillId must be set for `update` operation.", ResultType.INVALID);
        }

        if(!result.isSuccess()) {
            return result;
        }

        if(!repository.update(skill)) {
            String msg = String.format("skillId: %s, not found.", skill.getSkillId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int skillId) {
        return repository.deleteById(skillId);
    }

    public Result<Skill> validate(Skill skill) {
        Result<Skill> result = new Result<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Skill>> violations = validator.validate(skill);

        if(!violations.isEmpty()) {
            for(ConstraintViolation<Skill> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
        }

        return result;
    }
}
