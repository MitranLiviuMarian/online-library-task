package com.schwarz.onlinelibrary.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class BuyRequestDto {
    @NotNull
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyRequestDto that = (BuyRequestDto) o;
        return Objects.equals(customerId, that.customerId);
    }

    @Override
    public String toString() {
        return "BuyBookDto{" +
                "customerId=" + customerId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
