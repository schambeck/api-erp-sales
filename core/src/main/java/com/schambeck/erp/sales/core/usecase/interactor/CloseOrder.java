package com.schambeck.erp.sales.core.usecase.interactor;

import java.util.UUID;

public interface CloseOrder {
    void execute(UUID id);
}
