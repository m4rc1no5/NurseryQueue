package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Marcin Zaremba
 */
@RunWith(JUnitParamsRunner.class)
public class PageTest {

    @Test
    @Parameters(method = "provider")
    public void getUrl(Page page, String expectedUrl) {
        //when
        String url = page.getUrl();

        //then
        assertThat(url).isEqualTo(expectedUrl);
    }

    public static Object[][] provider() {
        return new Object[][] {
                { Page.LOGIN, "https://kolejka.gzz.hekko24.pl/api/v1.0/konto/login" },
                { Page.DICTIONARY, "https://kolejka.gzz.hekko24.pl/api/v1.0/slowniki?slowniki=%5B%22zlobek%22%5D" },
                { Page.ORDER, "https://kolejka.gzz.hekko24.pl/api/v1.0/zgloszenia/" }
        };
    }
}