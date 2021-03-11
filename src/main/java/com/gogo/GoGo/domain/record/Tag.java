package com.gogo.GoGo.domain.record;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gogo.GoGo.domain.community.Community;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"community"})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JsonBackReference
    private Community community;
}
