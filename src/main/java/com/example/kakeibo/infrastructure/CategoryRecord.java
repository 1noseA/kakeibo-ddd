package com.example.kakeibo.infrastructure;

import java.util.UUID;

public record CategoryRecord(
        UUID id,
        String name,
        String type,
        Boolean active
) {
}
