package uk.ac.ucl.cs.covid.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "PUBLIC.DEFAULT_MODEL")
public class DefaultModelEntity {

  @Id
  private Integer id;

  @Column(name="COUNTRY_ISO_A3", nullable = false, unique = true)
  private String countryIsoA3;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "MODEL_ID", referencedColumnName = "ID")
  private ModelEntity model;

  public ModelEntity getModel() {
    return model;
  }
}
