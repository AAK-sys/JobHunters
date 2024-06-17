package learn.resume_builder.data;

import learn.resume_builder.models.Education;

public interface EducationRepository {
    Education findById(int educationId);

    Education add(Education education);

    boolean update(Education education);

    boolean deleteById(int educationId);
}
