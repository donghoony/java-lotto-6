package lotto.domain;

import lotto.exception.ArgumentRangeOutOfBoundsException;

public class LottoBall {
    public static final int MIN_RANGE = 1;
    public static final int MAX_RANGE = 45;

    private final int number;

    private LottoBall(int number) {
        validateNumberRange(number);
        this.number = number;
    }

    public static LottoBall getInstance(int number) {
        if (LottoBallFactory.isExists(number)) {
            return LottoBallFactory.grabFromPool(number);
        } else {
            LottoBall lottoBall = new LottoBall(number);
            LottoBallFactory.addToPool(number, lottoBall);
            return lottoBall;
        }
    }

    private void validateNumberRange(int number) {
        validateNumberLowerRange(number);
        validateNumberUpperRange(number);
    }

    private void validateNumberLowerRange(int number) {
        if (number < MIN_RANGE) {
            throw new ArgumentRangeOutOfBoundsException();
        }
    }

    private void validateNumberUpperRange(int number) {
        if (number > MAX_RANGE) {
            throw new ArgumentRangeOutOfBoundsException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LottoBall lottoBall = (LottoBall) o;

        return number == lottoBall.number;
    }

    @Override
    public int hashCode() {
        return number;
    }
}
