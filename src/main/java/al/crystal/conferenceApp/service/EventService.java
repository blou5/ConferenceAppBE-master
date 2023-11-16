package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.mapper.EventMap;
import al.crystal.conferenceApp.mapper.EventMapper;
import al.crystal.conferenceApp.mapper.SpeakerMapper;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.model.Speaker;
import al.crystal.conferenceApp.repository.EventRepository;
import al.crystal.conferenceApp.repository.OrganiserRepository;
import al.crystal.conferenceApp.model.*;
import al.crystal.conferenceApp.repository.*;
import com.github.javafaker.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private OrganiserRepository organiserRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    ParticipantSessionRepository participantSessionRepository;


    public Event saveEvent(Event event) throws Exception {

        LocalDate today = LocalDate.now();
        if (today.isAfter(event.getStartDay())) {
            throw new Exception("The day to start is a past Day!");
        } else if (event.getStartDay().isAfter(event.getEndDay())) {
            throw new Exception("The day to start is a after the day to end!");
        }

        if (!(eventRepository.findEventsDate(event.getStartDay(), event.getEndDay()).isEmpty())) {
            throw new Exception
                    ("Between this Start Date and End Date there is another event!");
        }
        Event newEvent = Event.builder()
                .title(event.getTitle())
                .startDay(event.getStartDay())
                .endDay(event.getEndDay())
                .location(event.getLocation())
                .capacity(event.getCapacity())
                .eventImage(event.getEventImage())
                .description(event.getDescription())
                .organiser(event.getOrganiser())
                .speakers(new ArrayList<>())
                .build();
        System.out.println(event);
//        Optional<Organiser> organiserFoundById = organiserRepository.findById(newEvent.getOrganiser().getId());
        return this.eventRepository.save(newEvent);
    }

    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
//        return events.stream().map(event -> EventMapper.Instance.eventDTOToEvent(event)).collect(Collectors.toList());
    }

    public List<Event> eventToShow() {
        LocalDate today = LocalDate.now();
        if (!(eventRepository.eventToShowNow(today)).isEmpty()) {
            return eventRepository.eventToShowNow(today);
        } else {
            return eventRepository.eventsToShowAfter(today);
        }
    }

    public Event getEventById(Long id) {
        return this.eventRepository.findById(id).orElse(null);
    }

    public List<Event> deleteEvent(Long id) {
        this.eventRepository.deleteById(id);
        return this.eventRepository.findAll();
    }

    public List<Event> updateEvent(Event event) throws Exception{
        Event existingEvent = this.eventRepository.findById(event.getId()).orElse(null);
        if (existingEvent != null) {
            existingEvent.setTitle(event.getTitle());
            existingEvent.setStartDay(event.getStartDay());
            existingEvent.setEndDay(event.getEndDay());
            existingEvent.setLocation(event.getLocation());
            existingEvent.setCapacity(event.getCapacity());
            existingEvent.setEventImage(event.getEventImage());
            existingEvent.setDescription(event.getDescription());
            this.eventRepository.save(existingEvent);
        }
        return this.getAllEvents();
    }

    public List<EventDTO> getAllEventsByOrganiserId(Long id) {
        Optional<Organiser> optionalOrganiser = this.organiserRepository.findById(id);
        Organiser organiser = optionalOrganiser.get();

        List<Event> eventByOrganiser = this.eventRepository.findByOrganiser(organiser);
        return eventByOrganiser.stream().map(EventMap::toDto).collect(Collectors.toList());
    }

    public boolean subscribePlan(String subscriber, Long eventId, String plan) {
        Participant user = this.participantRepository.findByEmail(subscriber);
        if (user != null) {
            List<Session> sessionList = new ArrayList<>();
            if (plan.equalsIgnoreCase("full")) {
                sessionList = sessionRepository.findAllByEventId(eventId);
            } else {
                sessionList = sessionRepository.findByEventIdAndType(eventId, "session");
            }

            for (Session s : sessionList) {
                ParticipantSession ps = new ParticipantSession();
                ps.setParticipant(user);
                ps.setSession(s);
//                ParticipantSession byParticipantIdAndSessionId = participantSessionRepository.findByParticipantIdAndSessionId(user.getId(), s.getId());

//                Optional<Event> eventOptional = eventRepository.findById(eventId);
//                if (eventOptional.isPresent()) {
//                    Event event = eventOptional.get();
//                    List<Participant> participants = event.getParticipants();
//                    boolean exists = false;
//                    for (Participant p : participants) {
//                        if (p.getId() == user.getId()) {
//                            exists = true;
//                        }
//
//                        if (!exists) {
//                            participants.add(user);
//                            event.setParticipants(participants);
//                            eventRepository.saveAndFlush(event);
//                        }
//                    }
//
//
//                }

//                if (byParticipantIdAndSessionId != null) {
                    participantSessionRepository.saveAndFlush(ps);
//                }
            }
            return sessionList.size() > 0;
        }
        return false;
    }
}
