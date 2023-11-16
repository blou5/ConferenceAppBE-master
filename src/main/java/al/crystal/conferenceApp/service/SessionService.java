package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.SessionDTO;
import al.crystal.conferenceApp.mapper.SessionMapper;
import al.crystal.conferenceApp.mapper.SpeakerMapper;
import al.crystal.conferenceApp.model.*;
import al.crystal.conferenceApp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParticipantSessionRepository participantSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParticipantRepository participantRepository;


    public Session createSession(SessionDTO sessionDTO) {
        long selectedTrackId = sessionDTO.getTrack().getId();
        Track selectedTrack = trackRepository.findById(selectedTrackId).get();

        long selectedEventId = sessionDTO.getEvent().getId();
        Event selectedEvent = eventRepository.findById(selectedEventId).get();
        Session newSession = Session.builder()
                .description(sessionDTO.getDescription())
                .endTime(sessionDTO.getEndTime())
                .startTime(sessionDTO.getStartTime())
                .title(sessionDTO.getTitle())
                .capacity(sessionDTO.getCapacity())
                .track(selectedTrack)
                .event(selectedEvent)
                .type(sessionDTO.getType())
                .speakers(sessionDTO.getSpeakersDTO().stream().map(speaker -> SpeakerMapper.Instance.speaker(speaker)).collect(Collectors.toList()))
                .build();
        return sessionRepository.save(newSession);
    }

    public String addSpeakers(Long sessionId, List<Speaker> speakers) {
        Session session = sessionRepository.getReferenceById(sessionId);
        session.getSpeakers().addAll(speakers);
        sessionRepository.save(session);
        return "done";
    }

    public SessionDTO getOneSession(Long id) {
        Session session = sessionRepository.findById(id).get();
        SessionDTO sessionDTO = SessionMapper.Instance.sessionToSessionDTO(session);
        sessionDTO.setParticipation(participantSessionRepository.getParticipationForSession(sessionDTO.getId()));
        return sessionDTO;

    }

    private List<SessionDTO> getSessionsByDateBasedOnEvent(String date, Long id) {
        LocalDate localDate = stringToLocalDate(date);
        List<Session> sessionList = sessionRepository.findSessionsByStartTime(localDate, id);
        return sessionsToSessionsDTO(sessionList);
    }

    private List<SessionDTO> getSessionsByLocationBasedOnEvent(String location, Long id) {
        List<Session> sessionList = sessionRepository.findAllByTrackRoomLocationAndEventId(location, id);
        return sessionsToSessionsDTO(sessionList);
    }

    private List<SessionDTO> getSessionsByEvent(Long id) {
        List<Session> sessionList = sessionRepository.findAllByEventId(id);
        return sessionsToSessionsDTO(sessionList);
    }

    private List<SessionDTO> getSessionsByDateAndLocationBasedOnEvent(String date, String location, Long id) {
        LocalDate localDate = stringToLocalDate(date);
        List<Session> sessionList = sessionRepository.findAllByStartTimeAndTrackRoomLocation(localDate, location, id);
        return sessionsToSessionsDTO(sessionList);
    }

    private List<SessionDTO> getSessionsByDateAndLocation(String date, String location) {
        LocalDate localDate = stringToLocalDate(date);
        List<Session> sessionList = sessionRepository.findAllByDateAndLocation(localDate, location);
        return sessionsToSessionsDTO(sessionList);
    }


    public List<Session> getAllSessions() {
        return sessionRepository.findAll(Sort.by("startTime"));
    }

    public List<SessionDTO> getAllSessionsDTO() {
        List<Session> sessionList = sessionRepository.findAll(Sort.by("startTime"));
        List<SessionDTO> sessionDTOS = sessionsToSessionsDTO(sessionList);
        return sessionDTOS;
    }

    public List<SessionDTO> getSessions(String date, String location, Long id) {
        if (date == null && location == null && id != null) {
            return getSessionsByEvent(id);
        } else if (date != null && location == null && id != null) {
            return getSessionsByDateBasedOnEvent(date, id);
        } else if (date == null && location != null && id != null) {
            return getSessionsByLocationBasedOnEvent(location, id);
        } else if (date != null && location != null && id != null) {
            return getSessionsByDateAndLocationBasedOnEvent(date, location, id);
        } else if (date != null && location != null && id == null) {
            return getSessionsByDateAndLocation(date, location);
        } else if (date == null && location != null && id == null) {
            return getSessionsByLocation(location);
        } else if (date != null && location == null && id == null) {
            return getSessionsByDate(date);
        } else {
            List<Session> sessionList = getAllSessions();
            List<SessionDTO> sessionDTOS = sessionsToSessionsDTO(sessionList);
            sessionDTOS.forEach(sessionDTO ->
                    sessionDTO.setParticipation(participantSessionRepository.getParticipationForSession(sessionDTO.getId())));
            return sessionDTOS;
        }
    }

    private List<SessionDTO> getSessionsByDate(String date) {
        LocalDate localDate = stringToLocalDate(date);
        List<Session> sessionList = sessionRepository.findAllByDate(localDate);
        return sessionsToSessionsDTO(sessionList);
    }

    private List<SessionDTO> getSessionsByLocation(String location) {
        List<Session> sessionList = sessionRepository.findAllByTrackRoomLocation(location);
        return sessionsToSessionsDTO(sessionList);
    }


    public List<Session> saveSessionList(List<Session> sessionData) {
        return sessionRepository.saveAll(sessionData);
    }

    private List<SessionDTO> sessionsToSessionsDTO(List<Session> sessionList) {
        return sessionList.stream()
                .map(session -> SessionMapper.Instance.sessionToSessionDTO(session))
                .collect(Collectors.toList());
    }

    private LocalDate stringToLocalDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dateTimeFormatter);
    }

    public List<String> getSessionsDates(String location, Long eventId) {
        if (location != null && !location.equals("") && eventId == null) {
            return sessionRepository.findDistinctStartTimeBasedOnLocation(location);
        } else if (location != null && !location.equals("") && eventId != null) {
            return sessionRepository.findDistinctStartTimeBasedOnLocationAndEventId(location, eventId);
        } else if (location == null && eventId != null) {
            return sessionRepository.findDistinctStartTimeBasedOnEventId(eventId);
        } else {
            return sessionRepository.findDistinctStartTime();
        }
    }

    public List<String> getSessionsLocations(String date, Long eventId) {
        if (date != null && !date.equals("") && eventId == null) {
            return sessionRepository.findDistinctLocationBasedOnStartTime(date);
        } else if (date != null && !date.equals("") && eventId != null) {
            return sessionRepository.findDistinctLocationBasedOnStartTimeAndEventId(date, eventId);
        } else if (date == null && eventId != null) {
            return sessionRepository.findDistinctLocationBasedOnEventId(eventId);
        } else {
            return sessionRepository.findDistinctTrackRoomLocation();
        }
    }


    public void deleteSession(Long id) {

        participantSessionRepository.deleteBySessionId(id);
        sessionRepository.deleteById(id);
    }

    public SessionDTO updateSession(SessionDTO sessionDTO) {
        Session sessionOnDB = sessionRepository.findById(sessionDTO.getId()).get();

        sessionOnDB.setTitle(sessionDTO.getTitle());
        sessionOnDB.setDescription(sessionDTO.getDescription());
        sessionOnDB.setType(sessionDTO.getType());
        sessionOnDB.setCapacity(sessionDTO.getCapacity());
        sessionOnDB.setStartTime(sessionDTO.getStartTime());
        sessionOnDB.setEndTime(sessionDTO.getEndTime());
        sessionOnDB.setTrack(sessionDTO.getTrack());
        sessionOnDB.setSpeakers(sessionDTO.getSpeakersDTO().stream().map(speakerDTO -> SpeakerMapper.Instance.speaker(speakerDTO)).collect(Collectors.toList()));

        Session session = sessionRepository.saveAndFlush(sessionOnDB);
        return SessionMapper.Instance.sessionToSessionDTO(session);

    }

    public boolean rateSession(String email, Long sessionId, int rate) {
        Participant user = participantRepository.findByEmail(email);
        ParticipantSession participatedSession = participantSessionRepository.findByParticipantIdAndSessionId(user.getId(), sessionId);

        if (participatedSession != null) {
            if (participatedSession.getRating() != null) {
                return false;
            } else {
                participatedSession.setRating(rate);
                participantSessionRepository.saveAndFlush(participatedSession);
                return true;
            }
        }
        return false;
    }

    public Integer checkRatedSession(String email, Long sessionId) {
        Participant user = participantRepository.findByEmail(email);
        if (user != null) {
            ParticipantSession participatedSession = participantSessionRepository.findByParticipantIdAndSessionId(user.getId(), sessionId);
            if (participatedSession != null) {
                if (participatedSession.getRating() != null) {
                    return participatedSession.getRating();
                } else {
                    return null;
                }
            } else {
                return -1;
            }
        } else return -1;
    }
}
