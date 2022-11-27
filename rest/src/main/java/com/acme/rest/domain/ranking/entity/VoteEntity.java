package com.acme.rest.domain.ranking.entity;

import com.acme.core.ranking.domain.VoteKindEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
