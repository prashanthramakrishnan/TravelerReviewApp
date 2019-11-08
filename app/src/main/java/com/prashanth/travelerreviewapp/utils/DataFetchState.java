package com.prashanth.travelerreviewapp.utils;

public class DataFetchState {

    public enum Status {
        LOADING,
        SUCCESS,
        FAILURE
    }

    private final Status status;

    private final String message;

    public static final DataFetchState LOADED;

    public static final DataFetchState LOADING;

    public DataFetchState(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    static {
        LOADED = new DataFetchState(Status.SUCCESS, "Success");
        LOADING = new DataFetchState(Status.LOADING, "Running");
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
