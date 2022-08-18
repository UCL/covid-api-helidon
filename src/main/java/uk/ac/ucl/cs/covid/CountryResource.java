package uk.ac.ucl.cs.covid;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import uk.ac.ucl.cs.covid.model.chartjs.Root;

@Path("country/{countryCode}")
@RequestScoped
public class CountryResource {

  /**
   * ISO country code.
   * 3-letter format
   */
  private String countryCode;

  @PathParam("countryCode")
  public void setCountryCode(String aCountryCode) {
    this.countryCode = aCountryCode;
  }

  @Size(min = 3, max = 3)
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * Controller to call queries and build response objects.
   */
  @Inject
  private ResourceController controller;

  /**
   * Retrieve all model scores available for a country.
   * @return A JSON message with the scores.
   */
  @Path("data")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Root getDataForCountry() {
    return controller.getChartJsRoot(countryCode);
  }
}
