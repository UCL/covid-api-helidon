package uk.ac.ucl.cs.covid;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import uk.ac.ucl.cs.covid.model.CountryTotals;

@Path("world")
@RequestScoped
public class WorldResource {

  /**
   * Controller to call queries and build response objects.
   */
  @Inject
  private ResourceController controller;

  /**
   * Retrieve number of cases per 100K population per country.
   * @return the number of cases per country.
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<CountryTotals> getCountryTotals() {
    return controller.getTotalsPerCountry();
  }
}
