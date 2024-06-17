package learn.data;

import learn.models.Education;

public interface EducationRepository {
    Education findById(int educationId);

    Education add(Education education);

    boolean update(Education education);

    boolean deleteById(int educationId);
}
