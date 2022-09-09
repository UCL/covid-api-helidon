package uk.ac.ucl.cs.covid;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import uk.ac.ucl.cs.covid.model.ScoresRow;
import uk.ac.ucl.cs.covid.model.chartjs.Root;

/**
 * Resource of country specific data.
 * <p>
 * <code>GET /country/{countryCode}/data</code> All model scores for a given
 * country, by dataset.
 * <code>GET /country/{countryCode}/table</code> Model scores for a given
 * country, by date.
 * </p>
 */
@Path("country/{countryCode}")
@RequestScoped
public class CountryResource {

  /**
   * ISO country code.
   * 3-letter format
   */
  private String countryCode;

  /**
   * The expected size of the country code.
   */
  private static final int COUNTRY_CODE_SIZE = 3;

  /**
   * Sets the ISO A3 code of a country.
   * @param aCountryCode the ISO A3 code.
   */
  @PathParam("countryCode")
  public void setCountryCode(final String aCountryCode) {
    this.countryCode = aCountryCode;
  }

  /**
   * Gets and validates the ISO A3 country code.
   * @return the ISO A3 code.
   */
  @Size(min = COUNTRY_CODE_SIZE, max = COUNTRY_CODE_SIZE)
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * Controller to call queries and build response objects.
   */
  @Inject
  private ResourceController controller;

  /**
   * Retrieve all model scores available for a country, grouped by dataset.
   * @return A JSON message with the scores.
   */
  @Path("data")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Root getDataForCountry() {
    return controller.getChartJsRoot(countryCode);
  }

  /**
   * Retrieve the model scores available for a country, by date.
   * @return A JSON message with the scores as an array.
   */
  @Path("table")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public ScoresRow[] getScoresForCountry() {
    return controller.getScores(countryCode);
  }

}
