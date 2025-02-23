package com.academy.backend.exception.s3;

import com.academy.backend.exception.common.BaseException;
import org.springframework.http.HttpStatus;

public class FailedUploadException extends BaseException {
    public FailedUploadException() {
        super("Wrong input or error with reading file", "FAILED_TO_UPLOAD_FILE", HttpStatus.BAD_REQUEST);
    }
}
