package al.crystal.conferenceApp.mapper;

import al.crystal.conferenceApp.dto.EventDTO;
import al.crystal.conferenceApp.model.Event;


public class EventMap {

    public static EventDTO toDto(Event event) {
        return EventDTO.builder()
                .id(event.getId())
                .title(event.getTitle())
                .location(event.getLocation())
                .startDay(event.getStartDay())
                .endDay(event.getEndDay())
                .capacity(event.getCapacity())
                .organiserId(event.getOrganiser().getId())
                .build();
    }
}