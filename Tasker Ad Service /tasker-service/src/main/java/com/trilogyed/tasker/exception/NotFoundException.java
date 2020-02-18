package com.trilogyed.tasker.exception;


import org.springframework.web.bind.annotation.PutMapping;

import java.security.PublicKey;

/**
 * Exception class to handle not found cases in get APIs
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(int id) {super("There is no task with given taskId " + id); }

    public NotFoundException(String category){super("There are no tasks with the specified category "  + category);}

    public NotFoundException(){super("there are no tasks in the DB currently");}

}

