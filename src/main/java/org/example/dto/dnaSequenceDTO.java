package org.example.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class dnaSequenceDTO implements Serializable {

    private String[] dna;

}