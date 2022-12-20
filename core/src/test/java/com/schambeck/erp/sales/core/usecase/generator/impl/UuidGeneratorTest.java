package com.schambeck.erp.sales.core.usecase.generator.impl;

import com.schambeck.erp.sales.core.usecase.generator.impl.UuidGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UuidGeneratorTest {
    @InjectMocks
    UuidGenerator uuidGenerator;
    @Test
    void generate() {
        UUID generated = uuidGenerator.generate();

        assertNotNull(generated);
    }
}