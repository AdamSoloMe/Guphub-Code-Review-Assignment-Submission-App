package com.guphub.CodeReviewAssignmentSubmissionApp.enums;


import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AssignmentStatusEnum {

    //  pending submission, submitted, in review, needs update, completed
    PENDING_SUBMISSION("Pending Submission", 1),
    SUBMITTED("Submitted", 2),
    IN_REVIEW("In Review", 3),
    NEEDS_UPDATE("Needs Update", 4),
    COMPLETED("Completed", 5);
    private String status;
    private int id;

    AssignmentStatusEnum(String status, int id) {
        this.status = status;
        this.id = id;


    }

public String getStatus() {
        return status;
    }



    public int getId() {
        return id;
    }

    public static AssignmentStatusEnum fromString(String status) {
        for (AssignmentStatusEnum statusEnum : AssignmentStatusEnum.values()) {
            if (statusEnum.getStatus().equalsIgnoreCase(status)) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }
}
