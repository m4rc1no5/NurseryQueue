package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * @author Marcin Zaremba
 */
public class LocalDateRequest {

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate requestTime;

    public LocalDate getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDate requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LocalDateRequest{");
        sb.append("requestTime=").append(requestTime);
        sb.append('}');
        return sb.toString();
    }
}
