package al.crystal.conferenceApp.repository;

import al.crystal.conferenceApp.model.Organiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganiserRepository extends JpaRepository<Organiser, Long> {

    @Query(value = "Select o.id, o.biography, o.company_name, o.facebook_url,o.instagram_url,o.linkedin_url,o.tweeter_url from conference.organiser o where o.id =:id", nativeQuery = true)
    Organiser findOrganiserBy(Long id);
}
