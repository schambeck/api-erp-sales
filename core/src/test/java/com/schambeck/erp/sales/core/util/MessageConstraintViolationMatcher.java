package com.schambeck.erp.sales.core.util;

import org.hamcrest.Matcher;

import javax.validation.ConstraintViolation;

public final class MessageConstraintViolationMatcher {
    private MessageConstraintViolationMatcher() {
    }

    public static Matcher<ConstraintViolation<?>> messageIs(String message) {
        return new FuncTypeSafeMatcher<>(violation -> violation.getMessage().equals(message),
                (description) -> description.appendText("Message should be '%s'".formatted(message)),
                (violation, description) -> description.appendText("Was '%s'".formatted(violation.getMessage())));
    }
}
