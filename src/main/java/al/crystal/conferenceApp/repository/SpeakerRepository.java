package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    @Query(value = "Select * from speaker s where s.event_id =:eventId", nativeQuery = true)
    List<Speaker> findAllByEventId(@Param("eventId") Long eventId);
}