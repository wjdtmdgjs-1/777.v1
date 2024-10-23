package com.sparta.triple7api.trade.enums;

import com.sparta.triple7api.common.exception.InvalidRequestException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum TradeFor {
    SELF(TradeFor.Authority.SELF),
    OTHER(TradeFor.Authority.OTHER);

    private final String tradeType;

    public static TradeFor of(String role) {
        return Arrays.stream(TradeFor.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new InvalidRequestException("유효하지 않은 tradeFor"));
    }

    public static class Authority {
        public static final String SELF = "SELF";
        public static final String OTHER = "OTHER";
    }
}
