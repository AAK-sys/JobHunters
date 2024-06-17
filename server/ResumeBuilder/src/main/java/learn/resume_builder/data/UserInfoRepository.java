package learn.resume_builder.data;

import learn.resume_builder.models.UserInfo;

public interface UserInfoRepository {
    UserInfo findById(int userInfoId);

    UserInfo add(UserInfo userInfo);

    boolean update(UserInfo userInfo);

    boolean deleteById(int userInfoId);
}
