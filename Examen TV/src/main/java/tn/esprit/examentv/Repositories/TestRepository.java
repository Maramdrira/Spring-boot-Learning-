package tn.esprit.examentv.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examentv.Entities.TestExam;

public interface TestRepository extends JpaRepository<TestExam, Long> {

}
