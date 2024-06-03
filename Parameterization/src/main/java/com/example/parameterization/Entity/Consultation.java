package com.example.parameterization.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Integer con_ky;

    private String doctorName;

    private String descCon;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("consultations")
    @JoinTable(name = "consult_medic",
            joinColumns = {
                    @JoinColumn(name = "con_ky", referencedColumnName = "con_ky")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "medication_Ky", referencedColumnName = "medication_Ky")
            })
    private Set<Medication> medications = new HashSet<>();;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("consultations")
    @JoinTable(name = "consult_vacc",
            joinColumns = {
                    @JoinColumn(name = "con_ky", referencedColumnName = "con_ky")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "idVaccination", referencedColumnName = "idVaccination")
            })
    private Set<Vaccination> vaccinations = new HashSet<>();;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("consultations")
    @JoinTable(name = "consult_bio",
            joinColumns = {
                    @JoinColumn(name = "con_ky", referencedColumnName = "con_ky")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "id", referencedColumnName = "id")
            })
    private Set<BioAnalyses> analyses = new HashSet<>();;

    private String descPre ;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("consultations")
    @JoinTable(name = "consult_surg",
            joinColumns = {
                    @JoinColumn(name = "con_ky", referencedColumnName = "con_ky")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "CPT_Ky", referencedColumnName = "CPT_Ky")
            })
    private Set<SurgicalProcedure> surgicalProcedures = new HashSet<>();;

    private String descSur;

    private String comment;

    private Integer user_ky;

}
