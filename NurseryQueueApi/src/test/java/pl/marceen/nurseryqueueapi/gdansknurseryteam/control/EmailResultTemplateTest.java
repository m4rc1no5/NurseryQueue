package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.crud.entity.Result;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marcin Zaremba
 */
public class EmailResultTemplateTest {
    private static final Logger logger = LoggerFactory.getLogger(EmailResultTemplateTest.class);

    private EmailResultTemplate sut;

    @Before
    public void setUp() {
        sut = new EmailResultTemplate();
    }

    @Test
    public void getText() {
        //given
        Result actualResult = new Result();
        actualResult.setFirstNurseryName("Kasztanek");
        actualResult.setFirstNurseryStanding(123);
        actualResult.setSecondNurseryName("Biedronka");
        actualResult.setSecondNurseryStanding(132);
        actualResult.setThirdNurseryName("Żagielek");
        actualResult.setThirdNurseryStanding(136);

        Result lastResult = new Result();
        lastResult.setFirstNurseryStanding(124);
        lastResult.setSecondNurseryStanding(133);
        lastResult.setThirdNurseryStanding(137);

        //when
        String text = sut.getText(actualResult, lastResult);

        //then
        logger.info(text);
        assertThat(text).isNotEmpty();
    }
}