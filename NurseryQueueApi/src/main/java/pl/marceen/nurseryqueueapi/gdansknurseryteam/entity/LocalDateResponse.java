package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * @author Marcin Zaremba
 */
public class LocalDateResponse {

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate responseTime;

    public LocalDate getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(LocalDate responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocalDateResponse{");
        sb.append("responseTime=").append(responseTime);
        sb.append('}');
        return sb.toString();
    }
}
