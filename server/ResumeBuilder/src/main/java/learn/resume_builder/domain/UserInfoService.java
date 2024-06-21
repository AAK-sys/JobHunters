package learn.resume_builder.domain;

import learn.resume_builder.data.AppUserRepository;
import learn.resume_builder.data.UserInfoRepository;
import learn.resume_builder.models.UserInfo;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class UserInfoService {

    private final UserInfoRepository repository;
    private final AppUserRepository userRepository;

    public UserInfoService (UserInfoRepository repository, AppUserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public UserInfo findById(int userInfoId) {
        return repository.findById(userInfoId);
    }

    public Result<UserInfo> add(UserInfo userInfo) {
        Result<UserInfo> result = new Result<>();
        if(userInfo == null) {
            result.addMessage("UserInfo can not be null.", ResultType.INVALID);
            return result;
        }

        result = validate(userInfo);
        if(userInfo.getUserInfoId() != 0) {
            result.addMessage("userInfoId can not be set for `add` operation.", ResultType.INVALID);
        }

        if(userRepository.findById(userInfo.getUserId()) == null) {
            result.addMessage("userId does not associate with an existing user.", ResultType.INVALID);
        }

        if(!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(userInfo));
        return result;
    }

    public Result<UserInfo> update(UserInfo userInfo) {
        Result<UserInfo> result = new Result<>();
        if(userInfo == null) {
            result.addMessage("UserInfo can not be null.", ResultType.INVALID);
            return result;
        }

        result = validate(userInfo);

        if(userInfo.getUserInfoId() <= 0) {
            result.addMessage("userInfoId must be set for `update` operation.", ResultType.INVALID);
        }

        if(userRepository.findById(userInfo.getUserId()) == null) {
            result.addMessage("userId does not associate with an existing user.", ResultType.INVALID);
        }

        if(!result.isSuccess()) {
            return result;
        }

        if(!repository.update(userInfo)) {
            String msg = String.format("userInfoId: %s, not found.", userInfo.getUserInfoId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public boolean deleteById(int userInfoId) {
        return repository.deleteById(userInfoId);
    }

    public Result<UserInfo> validate(UserInfo userInfo) {
        Result<UserInfo> result = new Result<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);

        if(!violations.isEmpty()) {
            for(ConstraintViolation<UserInfo> violation : violations) {
                result.addMessage(violation.getMessage(), ResultType.INVALID);
            }
        }
        return result;
    }
}
