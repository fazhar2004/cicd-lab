package com.spring_boot.cicd_lab.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskRequest(
        @NotBlank String title
) {}
