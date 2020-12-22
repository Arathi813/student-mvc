package com.example.test.studentportal.exception;


public class StudentNotFoundException extends RuntimeException {

    //Todo: Is needed only if we want to store something explicitly other than message
    // while creating exception
//    private String testParam;
//
//    public CustomizedException(String message, String testParam) {
//        super(message);
//        this.testParam = testParam;
//    }
//
//    public String getTestParam() {
//        return testParam;
//    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
