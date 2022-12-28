package io.mend.sast.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Credentials {
    private String username;
    private String password;
}
