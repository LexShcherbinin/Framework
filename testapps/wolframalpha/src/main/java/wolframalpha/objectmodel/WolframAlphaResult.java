package wolframalpha.objectmodel;

import com.github.lexpalych.allure.webdriver.WebDriverPageObject;
import com.github.lexpalych.junit5.allure.steps.steprepositories.ButtonStepRepository;
import com.github.lexpalych.junit5.allure.steps.steprepositories.FieldStepRepository;

import static java.lang.Double.parseDouble;
import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WolframAlphaResult extends WebDriverPageObject<WolframAlphaResult> implements FieldStepRepository, ButtonStepRepository {
    private static final String CONTEXT = "//h2[contains(text(), 'Result')]/../.." + " | " + "//h2[contains(text(), 'Exact result')]/../..";
    private static final Double INACCURACY = 0.00000000000001;

    @Override
    public WolframAlphaResult checkFieldContainsValue(final String name, final String expectedValue) {
        final String xpath = CONTEXT + "//h2[contains(text(), 'Result')]/../..//div//img" + " | " +
                                CONTEXT + "//h2[contains(text(), 'Exact result')]/../..//div//img";

        waitUntilPageIsLoaded();
        final String currentValue = getWebElement(xpath).getAttribute("alt");

        assertTrue(abs(parseDouble(expectedValue) - parseDouble(currentValue)) < INACCURACY,
                "Ожидлось значение " + expectedValue + " , а получилось " + currentValue);
        return this;
    }
}
