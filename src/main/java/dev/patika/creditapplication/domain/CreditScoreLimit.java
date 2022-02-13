package dev.patika.creditapplication.domain;

public enum CreditScoreLimit {
    LOWER_LIMIT(500),
    UPPER_LIMIT(1000);

    private final Integer limit;

    CreditScoreLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }
}
