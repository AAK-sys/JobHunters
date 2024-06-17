package learn.resume_builder.data;

import learn.resume_builder.models.Experience;

public interface ExperienceRepository {
    Experience findById(int experienceId);

    Experience add(Experience experience);

    boolean update(Experience experience);

    boolean deleteById(int experienceId);
}
