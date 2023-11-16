package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.SpeakerRate;
import al.crystal.conferenceApp.model.SpeakerRateId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRateRepository extends JpaRepository<SpeakerRate, SpeakerRateId> {
}
