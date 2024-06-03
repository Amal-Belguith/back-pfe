package com.example.parameterization.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "monitoring")
public class Monitoring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer mon_ky;

    private String genInf;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("monitorings")
    @JoinTable(name = "monito_allerg",
            joinColumns = {
                    @JoinColumn(name = "mon_ky", referencedColumnName = "mon_ky")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "allergyKy", referencedColumnName = "allergyKy")
            })
    private Set<Allergy> Allergies = new HashSet<>();;

    private String height;
    private String weight;
    private String length_w;
    private String width_w;
    private String depth_w;
    private String length_s;
    private String width_s;
    private String depth_s;
    private String temperature;
    private String respiratory;
    private String heart;
    private String systolic;
    private String diastolic;
    private String gly;
    private String comment;
    private Integer user_ky;


}
