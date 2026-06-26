package com.example.kakeibo.domain.model.shared;

public record Memo(String value) {

    public Memo {
        if (value != null && value.length() > 255) {
            throw new IllegalArgumentException("メモは255文字以内で入力してください。");
        }
    }

    public static Memo of(String value) {
        return new Memo(value);
    }
}
