package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

import javax.json.bind.annotation.JsonbProperty;

/**
 * @author Marcin Zaremba
 */
public class DecodedData {

    @JsonbProperty("zgloszenie_id")
    private String applicationId;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DecodedData{");
        sb.append("applicationId='").append(applicationId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
