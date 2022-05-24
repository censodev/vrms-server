package io.github.censodev.vrms.vrmsserver.data.dto;

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
