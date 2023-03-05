package com.soon83;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.soon83.CommonResponse.ResponseType.SUCCESS;

@Getter
public class CommonResponse<T> {
    private final boolean success; // success: 200, 300, failure: 400, 500
    private final String code;
    private final String message;
    private T data;

    private CommonResponse(ResponseType responseType) {
        this.success = responseType.isSuccess();
        this.code = responseType.getCode();
        this.message = responseType.getMessage();
    }

    private CommonResponse(ResponseType responseType, T data) {
        this.success = responseType.isSuccess();
        this.code = responseType.getCode();
        this.message = responseType.getMessage();
        this.data = data;
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(SUCCESS);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(SUCCESS, data);
    }

    @Getter
    @RequiredArgsConstructor
    public enum ResponseType {
        SUCCESS(true, "성공"),
        FAILURE(false, "실패"),
        ;

        private final boolean success;
        private final String message;

        public String getCode() {
            return this.name();
        }
    }
}
