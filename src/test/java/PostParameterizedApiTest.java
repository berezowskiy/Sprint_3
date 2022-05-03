import model.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class PostParameterizedApiTest {
    private final Order order;
    private final int expected;

    private final PostSteps steps = new PostSteps();
    static List<String> bothColors = Arrays.asList("BLACK", "GREY");
    static List<String> blackColors = Arrays.asList("BLACK");

    public PostParameterizedApiTest(Order order, int expected) {
        this.order = order;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getResponseData() {
        return new Object[][] {
                {new Order("Dmitriy", "Autotesterman","Freedom bvd, 17 apt.",7,
                        "+78889219192",5,"2022-06-06",
                        "some comment for courier",bothColors), 201},
                {new Order("Dmitriy", "Autotesterman","Freedom bvd, 17 apt.",7,
                        "83339219192",5,"2022-06-03",
                        "some comment for courier",blackColors), 201},
                {new Order("Dmitriy", null,"Freedom bvd, 17 apt.",7,
                        null,5,"2022-06-01",
                        "pick color yourself",null), 201},

        };
    }

    @Test
    public void shouldBeOrderCreated() {
        int actual = steps.getOrderCreationStatusCode(order);
        assertEquals(expected, actual);
    }

}
