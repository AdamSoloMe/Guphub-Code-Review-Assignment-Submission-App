package com.guphub.CodeReviewAssignmentSubmissionApp.enums;

public enum AssignmentEnum {
    ASSIGNMENT_1(1, "Introduction to Java Basics"),
    ASSIGNMENT_2(2, "Object-Oriented Programming Concepts"),
    ASSIGNMENT_3(3, "Data Structures and Algorithms Challenge"),
    ASSIGNMENT_4(4, "Web Development: HTML and CSS"),
    ASSIGNMENT_5(5, "Interactive JavaScript Applications"),
    ASSIGNMENT_6(6, "Java Spring Boot REST API"),
    ASSIGNMENT_7(7, "Database Design and SQL Queries"),
    ASSIGNMENT_8(8, "User Authentication and Authorization"),
    ASSIGNMENT_9(9, "Frontend Frameworks: React.js"),
    ASSIGNMENT_10(10, "RESTful API Security and JWT"),
    ASSIGNMENT_11(11, "Mobile App Development: Flutter"),
    ASSIGNMENT_12(12, "Cloud Deployment and AWS"),
    ASSIGNMENT_13(13, "Machine Learning Model Implementation"),
    ASSIGNMENT_14(14, "Final Project: Full Stack Application"),
    ;

    private int assignmentNum;
    private String assignmentName;

    AssignmentEnum(int assignmentNum, String assignmentName) {
        this.assignmentNum = assignmentNum;
        this.assignmentName = assignmentName;
    }

    public int getAssignmentNum() {
        return assignmentNum;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public static AssignmentEnum getAssignmentEnumByNum(int assignmentNum) {
        for (AssignmentEnum value : AssignmentEnum.values()) {
            if (value.getAssignmentNum() == assignmentNum) {
                return value;
            }
        }
        return null; // Return null if not found
    }
    public static AssignmentEnum getAssignmentEnumByName(String assignmentName) {
        for (AssignmentEnum value : AssignmentEnum.values()) {
            if (value.getAssignmentName().equals(assignmentName)) {
                return value;
            }
        }
        return null; // Return null if not found
    }
}