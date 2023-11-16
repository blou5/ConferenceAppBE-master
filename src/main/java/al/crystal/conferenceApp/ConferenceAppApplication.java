package al.crystal.conferenceApp;

import al.crystal.conferenceApp.dto.OrganizerDTO;
import al.crystal.conferenceApp.faker.FakerDataAccess;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.repository.SpeakerRateRepository;
import al.crystal.conferenceApp.service.OrganizerService;
import al.crystal.conferenceApp.service.ParticipantService;
import al.crystal.conferenceApp.service.SpeakerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@Configuration
//@EnableJpaRepositories(basePackages = "al.crystal.conferenceApp")
public class ConferenceAppApplication implements CommandLineRunner {

    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private SpeakerRateRepository speakerRateRepository;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private SpeakerService speakerService;
//    @Autowired
//    private FakerDataAccess fakerDataAccess;
    public static void main(String[] args) {
        SpringApplication.run(ConferenceAppApplication.class, args);
    }
    Logger logger= LoggerFactory.getLogger(this.getClass().getName());
    @Override
    public void run(String... args) throws Exception {
//        OrganizerDTO organizerDTO=new OrganizerDTO("AdminName","AdminLastName","admin@admin.com","admin",
//                "CompanyName","Here is the biography",null,null,null,null);
//        organizerService.addOrganizer(organizerDTO);
//        Organiser organizer = organizerService.getOrganizer(1L);
//        logger.info(organizer.toString());
//        fakerDataAccess.createSessions(5,3,5,organizer,15);
    }
}
