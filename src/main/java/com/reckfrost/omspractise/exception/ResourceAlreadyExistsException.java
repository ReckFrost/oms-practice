package com.reckfrost.omspractise.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistsException extends RuntimeException{
    private final String resourceName;
    private final String fieldName;
    private final String fieldValue;

    public ResourceAlreadyExistsException(String resourceName, String fieldName, String fieldValue) {
        super(resourceName + " already exists with " + fieldName + " : " + fieldValue);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
