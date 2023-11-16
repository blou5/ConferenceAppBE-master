package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.mapper.SpeakerMapper;
import al.crystal.conferenceApp.model.*;
import al.crystal.conferenceApp.repository.ParticipantRepository;
import al.crystal.conferenceApp.repository.SpeakerRateRepository;
import al.crystal.conferenceApp.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private SpeakerRateRepository speakerRateRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    public String saveSpeaker(Speaker speaker) {
        speakerRepository.save(speaker);
        return "done";
    }

    public List<SpeakerDTO> saveListOfSpeaker(List<Speaker> speakers) {
        List<Speaker> speakers1 = speakerRepository.saveAll(speakers);
        return speakers1.stream().map(speaker -> SpeakerMapper.Instance.speakerDto(speaker)).collect(Collectors.toList());
    }

    public List<Speaker> saveAll(List<Speaker> speakers) {
        return speakerRepository.saveAll(speakers);
    }

    public List<SpeakerDTO> getAllSpeakers() {
        List<Speaker> speakers = speakerRepository.findAll();
        return speakers.stream().map(speaker -> SpeakerMapper.Instance.speakerDto(speaker)).collect(Collectors.toList());
    }


    public List<SpeakerDTO> getAllSpeakersByEvent(Long eventId) {
        List<Speaker> speakers = speakerRepository.findAllByEventId(eventId);
        return speakers.stream()
                .map(speaker -> SpeakerMapper.Instance.speakerDto(speaker))
                .collect(Collectors.toList());
    }

    public SpeakerRate rateSpeaker(SpeakerRate speakerRate, int rateSpeaker) {
        if (speakerRate.getRating() == null) {
            speakerRate.setRating(rateSpeaker);
            return speakerRateRepository.saveAndFlush(speakerRate);
        }
        return null;
    }

    public SpeakerRate findSpeakerRateById(String email, Long speakerId) {
        Participant user = this.participantRepository.findByEmail(email)
                ;
        if (user != null) {
            SpeakerRateId speakerRateId = new SpeakerRateId(user.getId(), speakerId);
            Optional<SpeakerRate> speakerRate = speakerRateRepository.findById(speakerRateId);
            if (speakerRate.isPresent()) {
                return speakerRate.get();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Integer checkRatedSpeaker(String email, Long speakerId) {
        Participant user = this.participantRepository.findByEmail(email)
                ;
        if (user != null) {
            SpeakerRateId speakerRateId = new SpeakerRateId(user.getId(), speakerId);
            Optional<SpeakerRate> speakerRate = speakerRateRepository.findById(speakerRateId);
            if (speakerRate.isPresent()) {
                if(speakerRate.get().getRating()!=null) {
                    return speakerRate.get().getRating();}
                else {
                    return  null;
                }
            } else {
                return -1;
            }
        }
        return -1;
    }
}