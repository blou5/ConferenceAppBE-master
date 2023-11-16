package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.ParticipantSession;
import al.crystal.conferenceApp.model.ParticipantSessionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ParticipantSessionRepository extends JpaRepository<ParticipantSession, ParticipantSessionId> {
    @Modifying
    @Transactional
    @Query(value = "Delete FROM participant_session ps where (ps.session_id)=:id", nativeQuery = true)
    void deleteBySessionId(Long id);

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE participant_session ps SET PS.rating =:rating WHERE ps.user_id = :userId AND ps.session_id = :sessionId", nativeQuery = true)
//    int updateRating (@Param("rating") long rating, @Param("userId") long userId, @Param("sessionId") long sessionId);

    @Query(value = "SELECT * FROM participant_session ps WHERE ps.participant_id = :userId AND ps.session_id = :sessionId", nativeQuery = true)
    ParticipantSession findByParticipantIdAndSessionId(@Param("userId") long userId, @Param("sessionId") long sessionId);

    @Query(value = "SELECT count(participant_id) FROM conference.participant_session where session_id=:sessionId",nativeQuery = true)
    Integer getParticipationForSession(@Param("sessionId") Long sessionId);
}
