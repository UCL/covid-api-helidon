package uk.ac.ucl.cs.covid;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import uk.ac.ucl.cs.covid.model.chartjs.Root;

public class CountryResource {

  /**
   * ISO country code.
   * 3-letter format
   */
  @PathParam("{countryCode}")
  private String countryCode;

  @Path("data")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Root getDataForCountry() {
    throw new UnsupportedOperationException("Not implemented");
  }
}
