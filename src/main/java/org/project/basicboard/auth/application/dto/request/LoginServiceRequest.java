package org.project.basicboard.auth.application.dto.request;

public record LoginServiceRequest(
        String username,
        String password
) {
}