package com.gogo.GoGo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gogo.GoGo.enumclass.PlaceStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"community"})
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PlaceStatus name;

    @ManyToOne
    @JsonBackReference
    private Community community;
}
