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

@RequestScoped
public class CountryResource {

  /**
   * ISO country code.
   * 3-letter format
   */
  @PathParam("{countryCode}")
  @Size(min = 3, max = 3)
  private String countryCode;

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
