package al.crystal.conferenceApp.mapper;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper Instance= Mappers.getMapper(EventMapper.class);

    EventDTO eventToEventDTO(Event event);
    Event eventDTOToEvent(EventDTO eventDTO);
}