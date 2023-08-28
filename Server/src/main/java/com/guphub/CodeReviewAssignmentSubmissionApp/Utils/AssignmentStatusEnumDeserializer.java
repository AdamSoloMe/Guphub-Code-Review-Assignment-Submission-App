package com.guphub.CodeReviewAssignmentSubmissionApp.Utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.guphub.CodeReviewAssignmentSubmissionApp.enums.AssignmentStatusEnum;

import java.io.IOException;

public class AssignmentStatusEnumDeserializer extends JsonDeserializer<AssignmentStatusEnum> {
    @Override
    public AssignmentStatusEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String statusString = jsonParser.getText();
        return AssignmentStatusEnum.fromString(statusString);
    }
}
