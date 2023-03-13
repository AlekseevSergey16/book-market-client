package com.salekseev.booksmarketclient.model;

public class Result<T> {
    private final T data;
    private final ErrorInfo error;

    private Result(T data, ErrorInfo error) {
        this.data = data;
        this.error = error;
    }

    public static <U> Result<U> ok(U data) {
        return new Result<>(data, null);
    }

    public static <U> Result<U> error(ErrorInfo error) {
        return new Result<>(null, error);
    }

    public T getData() {
        return data;
    }

    public ErrorInfo getError() {
        return error;
    }
}
