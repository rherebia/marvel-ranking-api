package com.acme.rest.domain.ranking.entity;

import com.acme.core.ranking.domain.VoteKindEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "votes")
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "character_id")
    private Long characterId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "vote_kind")
    private VoteKindEnum voteKind;
}
