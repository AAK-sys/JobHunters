package learn.data;

import learn.models.Experience;

public interface ExperienceRepository {
    Experience findById(int experienceId);

    Experience add(Experience experience);

    boolean update(Experience experience);

    boolean deleteById(int experienceId);
}
