package com.xworkz.finalProject.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "imagestore")
@NamedQuery(name = "findByUserId",query = "select profile from ProfileDTO profile where profile.userId = :userId")
@NamedQuery(name = "findDatasByUserId",query = "select profile from ProfileDTO profile where profile.userId = :userId")
@NamedQuery(name = "updateStatus",query = "update ProfileDTO  set status='InActive' where userId=:id")
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String status;
}
