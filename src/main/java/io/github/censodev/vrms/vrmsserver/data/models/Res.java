package io.github.censodev.vrms.vrmsserver.data.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Res<T> {
    T data;
    String message;
}
