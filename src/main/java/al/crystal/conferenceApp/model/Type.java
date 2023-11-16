package al.crystal.conferenceApp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum Type {
    ORGANISER(Values.ORGANISER),
    PARTICIPANT(Values.PARTICIPANT);

    private String value;

    public static class Values {
        public static final String ORGANISER = "O";
        public static final String PARTICIPANT = "P";
    }
}
