package com.xworkz.finalProject.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "imagestore")
@NamedQuery(name = "findByUserId",query = "select profile from ProfileDTO profile where profile.userId = :userId")
public class ProfileDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String imageName;
    @Column(name = "size")
    private double imageSize;
    @Column(name = "image_type")
    private String imageType;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
}
