package org.vanbart.hellospark;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HelloDto {

    private final String status;

    private final String message;
}
