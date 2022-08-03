package uk.ac.ucl.cs.covid;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.container.ResourceContext;
import jakarta.ws.rs.core.Context;

public class RootResource {

  /**
   * Resource context to forward requests to subresources.
   */
  @Context
  private ResourceContext resourceContext;

  /**
   * Forward request to resource for country-specific data.
   * @return a JAXRS resource for country-specific data
   */
  @Path("/country/{countryCode}")
  public CountryResource toCountryResource() {
    return resourceContext.getResource(CountryResource.class);
  }

}
