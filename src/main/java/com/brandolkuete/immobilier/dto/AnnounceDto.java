package com.brandolkuete.immobilier.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnounceDto {
    Long id;
    String title;
    String description;
    Date publicationDate;

    ImageDto image;
}
