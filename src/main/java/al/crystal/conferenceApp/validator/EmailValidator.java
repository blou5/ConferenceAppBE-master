package al.crystal.conferenceApp.validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;

public class EmailValidator {

    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public boolean isCompanyEmail(String email) {
        List<String> domainNames = List.of("gmail", "aol", "msn", "wanadoo", "orange", "comcast", "rediffmail",
                "free", "web", "yandex", "ymail", "libero", "outlook", "uol", "bol", "cox", "sbcglobal", "sfr",
                "verizon", "googlemail", "ig", "terra", "neuf", "alice", "rocketmail", "att", "laposte", "facebook",
                "bellsouth", "charter", "rambler", "shaw", "sky", "earthlink", "optonline", "freenet", "t-online",
                "aliceadsl", "virgilio", "home", "qq", "telenet", "me", "voila", "planet", "tin", "ntlworld", "arcor",
                "frontiernet", "hetnet", "zonnet", "club-internet", "juno", "optusnet", "blueyonder", "bluewin",
                "skynet", "sympatico", "windstream", "mac", "centurytel", "chello", "aim", "icloud");
       return domainNames.stream().anyMatch(s -> s.toLowerCase().contains(email));
    }
}
