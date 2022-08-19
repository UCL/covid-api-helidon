package uk.ac.ucl.cs.covid.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representation of a model.
 * Multiple models are allowed per country, they can be enabled (displayed), or
 * disabled (not displayed).
 */
@Entity
@Table(name = "PUBLIC.MODEL")
public class ModelEntity {

  /**
   * The table identifier.
   */
  @Id
  private Integer id;

  /**
   * The name of the model.
   */
  @Column(name = "MODEL_NAME", nullable = false)
  private String modelName;

  /**
   * Whether the model should be displayed by the frontends or not.
   * It should be considered as enabled or disabled.
   */
  @Column(name = "IS_DISPLAYED", nullable = false)
  private Boolean isDisplayed;

  /**
   * ISO A3 code for a country.
   * Its value is used for querying data associated to a model.
   */
  @Column(name = "COUNTRY_ISO_A3", nullable = false)
  private String countryIsoA3;

  /**
   * Returns the table identifier.
   * @return the ID of the entity
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the table identifier.
   * @param anId the ID of the entity
   */
  public void setId(final Integer anId) {
    this.id = anId;
  }

  /**
   * Returns the name of the model.
   * @return the name of the model
   */
  public String getModelName() {
    return modelName;
  }

  /**
   * Sets the name of the model. It expects a non-empty String.
   * @param aModelName the name of the model.
   */
  public void setModelName(final String aModelName) {
    this.modelName = aModelName;
  }

  /**
   * Whether the model should be displayed by the frontend.
   * @return true if it should be displayed, false otherwise.
   */
  public Boolean getDisplayed() {
    return isDisplayed;
  }

  /**
   * Enables or disables the model for display.
   * @param displayed true if it should be displayed, false otherwise.
   */
  public void setDisplayed(final Boolean displayed) {
    isDisplayed = displayed;
  }

  /**
   * Returns the ISO A3 code of the country associated with this model.
   * @return the ISO A3 code.
   */
  public String getCountryIsoA3() {
    return countryIsoA3;
  }

  /**
   * Sets the ISO A3 code of the country associated with this model.
   * @param aCountryIsoA3 the ISO A3 code.
   */
  public void setCountryIsoA3(final String aCountryIsoA3) {
    this.countryIsoA3 = aCountryIsoA3;
  }
}
