package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @Query(value = "Select * from session s where DATE(s.start_time) =:startDate and s.event_id =:id", nativeQuery = true)
    List<Session> findSessionsByStartTime(LocalDate startDate, Long id);

    List<Session> findAllByTrackRoomLocationAndEventId(String roomLocation, Long id);

    @Query(value = "Select * from session s JOIN tracks t  ON (t.id=s.track_id) where DATE(s.start_time) =:startDate and (t.room_location)=:roomLocation and s.event_id=:id ", nativeQuery = true)
    List<Session> findAllByStartTimeAndTrackRoomLocation(LocalDate startDate, String roomLocation, Long id);

    @Query("SELECT DISTINCT DATE(s.startTime) FROM session s")
    List<String> findDistinctStartTime();

    @Query(value = "SELECT DISTINCT DATE(s.start_time) FROM session s JOIN tracks t ON (t.id=s.track_id) where (t.room_location)=:roomLocation", nativeQuery = true)
    List<String> findDistinctStartTimeBasedOnLocation(String roomLocation);

    @Query("SELECT DISTINCT (t.roomLocation) FROM tracks t")
    List<String> findDistinctTrackRoomLocation();

    @Query(value = "SELECT DISTINCT (t.room_location) FROM tracks t JOIN session s ON (t.id=s.track_id) where DATE(s.start_time) =:startDate", nativeQuery = true)
    List<String> findDistinctLocationBasedOnStartTime(String startDate);

    List<Session> findAllByEventId(Long id);

    @Query(value = "Select * from session s JOIN tracks t  ON (t.id=s.track_id) where DATE(s.start_time) =:startDate and (t.room_location)=:roomLocation", nativeQuery = true)
    List<Session> findAllByDateAndLocation(LocalDate startDate, String roomLocation);


    List<Session> findAllByTrackRoomLocation(String location);


    @Query(value = "Select * from session s where DATE(s.start_time) =:startDate", nativeQuery = true)
    List<Session> findAllByDate(LocalDate startDate);

    @Query(value = "SELECT DISTINCT DATE(s.start_time) FROM session s JOIN tracks t ON (t.id=s.track_id) where (t.room_location)=:roomLocation and s.event_id=:eventId", nativeQuery = true)
    List<String> findDistinctStartTimeBasedOnLocationAndEventId(String roomLocation, Long eventId);

    @Query(value = "SELECT DISTINCT DATE(s.start_time) FROM session s where s.event_id=:eventId", nativeQuery = true)
    List<String> findDistinctStartTimeBasedOnEventId(Long eventId);

    @Query(value = "SELECT DISTINCT (t.room_location) FROM tracks t JOIN session s ON (t.id=s.track_id) where DATE(s.start_time) =:startDate and s.event_id=:eventId", nativeQuery = true)
    List<String> findDistinctLocationBasedOnStartTimeAndEventId(String startDate, Long eventId);

    @Query(value = "SELECT DISTINCT (t.room_location) FROM tracks t JOIN session s ON (t.id=s.track_id) where s.event_id=:eventId", nativeQuery = true)
    List<String> findDistinctLocationBasedOnEventId(Long eventId);

    List<Session> findByEventIdAndType(Long eventId, String type);


}


