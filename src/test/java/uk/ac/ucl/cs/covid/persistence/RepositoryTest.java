package uk.ac.ucl.cs.covid.persistence;

import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@HelidonTest
public class RepositoryTest {

  @Inject
  private Repository repository;

  @Test
  public void testFindAllModelScoresForModelId() {
    var actual = repository.findAllModelScoresForModelId(1);
    assertThat("Two rows of ModelScores", actual.size(), is(2));
  }
}
