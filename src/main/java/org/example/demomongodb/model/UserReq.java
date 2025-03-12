package org.example.demomongodb.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {
    private String id;
    private String name;
}
