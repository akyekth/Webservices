package general;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class MyApplicationConfiguration extends Configuration {
    @NotEmpty
    private String defaultName = "Fk is thiis";

    @JsonProperty
    public String getDefaultName() { return this.defaultName; }

    @JsonProperty
    public void setDefaultName(String defaultName) { this.defaultName = defaultName; }
}
