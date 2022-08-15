package uk.ac.ucl.cs.covid.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PUBLIC.MODEL")
public class ModelEntity {
  @Id
  private Integer id;

  @Column(name = "MODEL_NAME", nullable = false)
  private String modelName;

  @Column(name="IS_DISPLAYED", nullable = false)
  private Boolean isDisplayed;

  @Column(name="COUNTRY_ISO_A3", nullable = false)
  private String countryIsoA3;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String aModelName) {
    this.modelName = aModelName;
  }

  public Boolean getDisplayed() {
    return isDisplayed;
  }

  public void setDisplayed(Boolean displayed) {
    isDisplayed = displayed;
  }

  public String getCountryIsoA3() {
    return countryIsoA3;
  }

  public void setCountryIsoA3(String aCountryIsoA3) {
    this.countryIsoA3 = aCountryIsoA3;
  }
}