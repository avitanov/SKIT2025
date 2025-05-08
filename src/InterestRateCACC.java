import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InterestRateCACC {

    private static Stream<Arguments> testData() {
        return Stream.of(
                // Row 3: a=T, b=T, c=F, d=T => P=T
                Arguments.of(750, false, 15000.0, 2, 5.0),   // 3.5 + 1.5

                // Row 11: a=F, b=T, c=T, d=F => P=F
                Arguments.of(690, false, 15000.0, 5, 9.5),   // 6.5 + 3.0

                // Row 13: a=T, b=F, c=T, d=T => P=T
                Arguments.of(750, true, 15000.0, 5, 5.0),    // 3.5 + 1.5

                // Row 1: a=F, b=T, c=T, d=T => P=F
                Arguments.of(650, false, 9000.0, 5, 4.4),    // 3.5 + 1.8

                // Row 6: a=F, b=F, c=F, d=T => P=F
                Arguments.of(650, true, 9000.0, 2, 8.3)      // 6.5 + 1.8
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void testCalculateAdjustedInterestRate(int creditScore, boolean isFirstTimeBorrower, double loanAmount, int yearsEmployed, double expected) {
        double actual = InterestRateTest.calculateAdjustedInterestRate(creditScore, isFirstTimeBorrower, loanAmount, yearsEmployed);
        assertEquals(expected, actual);
    }
}
