package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import pl.marceen.nurseryqueueapi.crud.entity.Result;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

/**
 * @author Marcin Zaremba
 */
public class EmailResultTemplate {

    private static final String TITLE = "[NurseryQueue] Wykryto zmianę w kolejce do żłobka (Gdański Zespół Żłobkow)";

    String getTitle() {
        return TITLE;
    }

    String getText(Result actualResult, Result lastResult) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        StringJoiner stringJoiner = new StringJoiner("\n");

        stringJoiner.add(String.format("Aktualne miejsce w kolejkach - stan na %s:", LocalDateTime.now().format(formatter)));
        stringJoiner.add("");
        stringJoiner.add(getRow(actualResult.getFirstNurseryName(), actualResult.getFirstNurseryStanding(), lastResult.getFirstNurseryStanding()));
        stringJoiner.add(getRow(actualResult.getSecondNurseryName(), actualResult.getSecondNurseryStanding(), lastResult.getSecondNurseryStanding()));
        stringJoiner.add(getRow(actualResult.getThirdNurseryName(), actualResult.getThirdNurseryStanding(), lastResult.getThirdNurseryStanding()));

        return stringJoiner.toString();
    }

    private String getRow(String nursery, int actualStanding, int lastStanding) {
        return String.format("- %s - miejsce: %s (poprzednio: %s)", nursery, actualStanding, lastStanding);
    }
}
