package uk.ac.ucl.cs.covid.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Representation of the default model for a given country.
 */
@Entity
@Table(name = "PUBLIC.DEFAULT_MODEL")
public class DefaultModelEntity {

  /**
   * The table identifier.
   */
  @Id
  private Integer id;

  /**
   * ISO A3 code for a country.
   * Its value is used for querying data associated to a model.
   */
  @Column(name = "COUNTRY_ISO_A3", nullable = false, unique = true)
  private String countryIsoA3;

  /**
   * Reference to a model.
   * Only one model per country allowed.
   */
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "MODEL_ID", referencedColumnName = "ID")
  private ModelEntity model;

  /**
   * Returns the model entity set as default for this country.
   * @return the referenced {@link ModelEntity}
   */
  public ModelEntity getModel() {
    return model;
  }

}
