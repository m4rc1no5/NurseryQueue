package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

import javax.json.bind.annotation.JsonbProperty;

/**
 * @author Marcin Zaremba
 */
public class OrderResponse {
    @JsonbProperty("zgloszenie_id")
    private String applicationId;

    @JsonbProperty("nastepne_potwierdzenie_od")
    private String nextConfirmationFrom;

    @JsonbProperty("zlobek_1_nazwa")
    private String firstNurseryName;
    @JsonbProperty("zlobek_1_pozycja")
    private String firstNurseryStanding;

    @JsonbProperty("zlobek_2_nazwa")
    private String secondNurseryName;
    @JsonbProperty("zlobek_2_pozycja")
    private String secondNurseryStanding;

    @JsonbProperty("zlobek_3_nazwa")
    private String thirdNurseryName;
    @JsonbProperty("zlobek_3_pozycja")
    private String thirdNurseryStanding;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getNextConfirmationFrom() {
        return nextConfirmationFrom;
    }

    public void setNextConfirmationFrom(String nextConfirmationFrom) {
        this.nextConfirmationFrom = nextConfirmationFrom;
    }

    public String getFirstNurseryName() {
        return firstNurseryName;
    }

    public void setFirstNurseryName(String firstNurseryName) {
        this.firstNurseryName = firstNurseryName;
    }

    public String getFirstNurseryStanding() {
        return firstNurseryStanding;
    }

    public void setFirstNurseryStanding(String firstNurseryStanding) {
        this.firstNurseryStanding = firstNurseryStanding;
    }

    public String getSecondNurseryName() {
        return secondNurseryName;
    }

    public void setSecondNurseryName(String secondNurseryName) {
        this.secondNurseryName = secondNurseryName;
    }

    public String getSecondNurseryStanding() {
        return secondNurseryStanding;
    }

    public void setSecondNurseryStanding(String secondNurseryStanding) {
        this.secondNurseryStanding = secondNurseryStanding;
    }

    public String getThirdNurseryName() {
        return thirdNurseryName;
    }

    public void setThirdNurseryName(String thirdNurseryName) {
        this.thirdNurseryName = thirdNurseryName;
    }

    public String getThirdNurseryStanding() {
        return thirdNurseryStanding;
    }

    public void setThirdNurseryStanding(String thirdNurseryStanding) {
        this.thirdNurseryStanding = thirdNurseryStanding;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderResponse{");
        sb.append("applicationId='").append(applicationId).append('\'');
        sb.append(", nextConfirmationFrom='").append(nextConfirmationFrom).append('\'');
        sb.append(", firstNurseryName='").append(firstNurseryName).append('\'');
        sb.append(", firstNurseryStanding='").append(firstNurseryStanding).append('\'');
        sb.append(", secondNurseryName='").append(secondNurseryName).append('\'');
        sb.append(", secondNurseryStanding='").append(secondNurseryStanding).append('\'');
        sb.append(", thirdNurseryName='").append(thirdNurseryName).append('\'');
        sb.append(", thirdNurseryStanding='").append(thirdNurseryStanding).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
