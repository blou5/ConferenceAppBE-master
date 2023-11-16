package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.ParticipantDTO;
import al.crystal.conferenceApp.mapper.ParticipantMapper;
import al.crystal.conferenceApp.model.Participant;
import al.crystal.conferenceApp.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public List<Participant> saveParticipant(List<ParticipantDTO> participantDTOS) {
        List<Participant> collect = participantDTOS.stream().map(ParticipantMapper.Instance::participant)
                .collect(Collectors.toList());

        return participantRepository.saveAll(collect);
    }

    public List<Participant> getParticipants() {
        return participantRepository.findAll();
    }
}
