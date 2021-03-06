package ca.uottawa.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;
}
