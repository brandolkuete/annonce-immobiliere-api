package com.brandolkuete.immobilier.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "announce")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Announce {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable=false, length=5000)
    private String title;

    @Column(name = "description", nullable=false)
    @Lob
    private String description;

    @Column(name = "publication_date")
    private Date publicationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

}
