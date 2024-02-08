package sh.alex.onlineTesting.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sh.alex.onlineTesting.model.entities.AnswerEntity;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    @Query("SELECT a FROM AnswerEntity a WHERE a.questionId = :questionId")
    public List<AnswerEntity> getByQuestionId (@Param("questionId") Long questionId);
}
