package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

}
