package al.crystal.conferenceApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity(name = "organiser")
@DiscriminatorValue(value = "O")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Organiser extends User implements Serializable {
    private String companyName;
    private String biography;
    private String linkedinUrl;
    private String tweeterUrl;
    private String facebookUrl;
    private String instagramUrl;
    @JsonManagedReference
    @OneToMany(mappedBy = "organiser")
    private List<Event> events;

    public Organiser(String firstName, String lastName, String email, String password, String companyName,
                     String biography, String linkedinUrl, String tweeterUrl, String facebookUrl, String instagramUrl) {
        super(firstName, lastName, email, password);
        this.companyName = companyName;
        this.biography = biography;
        this.linkedinUrl = linkedinUrl;
        this.tweeterUrl = tweeterUrl;
        this.facebookUrl = facebookUrl;
        this.instagramUrl = instagramUrl;
    }
}
