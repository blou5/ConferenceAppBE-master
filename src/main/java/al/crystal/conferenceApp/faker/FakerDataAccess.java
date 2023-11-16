package al.crystal.conferenceApp.faker;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.dto.ParticipantDTO;
import al.crystal.conferenceApp.dto.SessionDTO;
import al.crystal.conferenceApp.dto.TrackDTO;
import al.crystal.conferenceApp.dto.speaker.SpeakerParticipantRateDTO;
import al.crystal.conferenceApp.mapper.EventMapper;
import al.crystal.conferenceApp.mapper.SessionMapper;
import al.crystal.conferenceApp.mapper.SpeakerMapper;
import al.crystal.conferenceApp.mapper.SpeakerParticipantRateMapper;
import al.crystal.conferenceApp.model.*;
import al.crystal.conferenceApp.repository.ParticipantSessionRepository;
import al.crystal.conferenceApp.repository.SpeakerRateRepository;
import al.crystal.conferenceApp.service.*;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class FakerDataAccess {

    @Autowired
    private EventService eventService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SpeakerService speakerService;
    @Autowired
    private TrackService trackService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private SpeakerRateRepository speakerRateRepository;

    @Autowired
    private ParticipantSessionRepository participantSessionRepository;

    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());
    Faker faker = Faker.instance();


    public List<Session> createSessions(int numberOfSessions, int numberOfTracks, int numberOfSpeakers, Organiser organiser, int numberOfParticipants) {

        EventDTO event1 = createEvent(organiser);
        Event evententity = EventMapper.Instance.eventDTOToEvent(event1);
        Event event = null;
        try {
            event = eventService.saveEvent( evententity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Track> tracks = trackService.saveTracks(trackDTOList(numberOfTracks));
        List<Speaker> speakers = speakerService.saveAll(speakerList(numberOfSpeakers, event));
        List<Session> sessions = sessionList(numberOfSessions, event, tracks, speakers);
        createSpeakerRate(numberOfParticipants);
        createParticipantSessions();
        return sessions;
    }

    public List<Session> sessionList(int numberOfSession, Event event, List<Track> tracks, List<Speaker> speakers) {
        List<SessionDTO> sessionData = IntStream.range(0, numberOfSession).mapToObj(date -> SessionDTO.builder()
                .title(faker.lorem().word())
                .capacity(faker.random().nextInt(10, 590))
                .startTime(getFutureDay(1))
                .endTime(getFutureDay(1))
                .event(event)
                .description(faker.lorem().paragraph())
                .type(random(List.of("session","workshop")))
                .build()).collect(Collectors.toList());
        List<Session> sessions = sessionData.stream().map(sessionDTO -> SessionMapper.Instance.sessionDTOToSession(sessionDTO)).collect(Collectors.toList());

        sessions.forEach(session -> {
            session.setTrack(random(tracks));
            session.setSpeakers(randomList(speakers, 0.5f));
        });

        return sessionService.saveSessionList(sessions);

    }

    public EventDTO createEvent(Organiser organiser) {
        int capacity = faker.random().nextInt(500);
        return new EventDTO(1L, "title", getPastDay(5),
                getPastDay(2),
                faker.address().fullAddress(),
                capacity, organiser.getId(), new ArrayList<>());
    }

    private LocalDate getPastDay(int day) {
        return LocalDate.ofInstant(Instant.ofEpochMilli(faker.date().past(day, TimeUnit.DAYS).getTime()), ZoneId.systemDefault());
    }


    public List<TrackDTO> trackDTOList(int numberOfTrack) {
        return IntStream.range(0, numberOfTrack)
                .mapToObj(data -> new TrackDTO(null,faker.funnyName().name(),
                        faker.address().streetName(), "none"))
                .collect(Collectors.toList());
    }

    private LocalDateTime getFutureDay(int day) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(faker.date().future(day, TimeUnit.DAYS).getTime()), ZoneId.systemDefault());
    }

    public List<Speaker> speakerList(int numberOfSpeakers, Event event) {
        return IntStream.range(0, numberOfSpeakers).mapToObj(data -> Speaker.builder()
                .name(faker.name().firstName())
                .lastName(faker.name().lastName())
                .biography(faker.lorem().characters(100, 200))
                .companyName(faker.company().name())
                .title(faker.name().title())
                .events(event)
                .build()).collect(Collectors.toList());
    }

    public <T> T random(List<T> t) {
        return t.get(faker.random().nextInt(t.size()));
    }

    public <T> List<T> randomList(List<T> t, float percentage) {
        int totalPercentage = Math.round(percentage * t.size());
        return IntStream.range(0, totalPercentage).mapToObj(data -> random(t)).collect(Collectors.toList());
    }

    public List<ParticipantDTO> createParticipant(int numberOfParticipant) {
        return IntStream.range(0, numberOfParticipant)
                .mapToObj(i -> ParticipantDTO.builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(email())
                        .password(faker.name().username())
                        .build()).collect(Collectors.toList());
    }

    public List<SpeakerParticipantRateDTO> speakerRate(List<Participant> participants) {
        return speakerService.getAllSpeakers().stream().flatMap(speakerDTO ->
                participants.stream().map(participant ->
                        new SpeakerParticipantRateDTO(
                                participant,
                                SpeakerMapper.Instance.speaker(speakerDTO), null)
                )
        ).collect(Collectors.toList());
    }


    public List<SpeakerRate> createSpeakerRate(int numberOfParticipant) {
        List<ParticipantDTO> participant = createParticipant(numberOfParticipant);
        List<Participant> participants = participantService.saveParticipant(participant);
        List<SpeakerParticipantRateDTO> speakerParticipantRateDTOS = speakerRate(participants);
        List<SpeakerRate> speakerRateList = speakerParticipantRateDTOS.stream()
                .map(speakerParticipantRateDTO -> SpeakerParticipantRateMapper.Instance.speakerRate(speakerParticipantRateDTO))
                .collect(Collectors.toList());
        return speakerRateRepository.saveAll(speakerRateList);

    }

    public List<ParticipantSession> createParticipantSessions() {

        List<Participant> participants = participantService.getParticipants();
        List<Session> allSessions = sessionService.getAllSessions();
        List<ParticipantSession> participantSessionList = allSessions.stream().flatMap(session -> participants.stream()
                .map(participant1 -> new ParticipantSession(random(List.of(1, 2, 3, 4, 5)), session, participant1))
        ).collect(Collectors.toList());
        return participantSessionRepository.saveAll(participantSessionList);
    }

    public String email() {
        return fakeValuesService.bothify("????##@gmail.com");
    }
}
