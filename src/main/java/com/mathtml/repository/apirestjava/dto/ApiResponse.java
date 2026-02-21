package com.mathtml.repository.apirestjava.dto;

public class ApiResponse {

    private String message;
    private int status;
    private String title;

    public ApiResponse(String message, int status, String title) {
        this.message = message;
        this.status = status;
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }
}