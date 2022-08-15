package uk.ac.ucl.cs.covid.persistence;

import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.eclipse.persistence.internal.weaving.PersistenceWeaved;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

@HelidonTest
public class WeavingTest {

  @PersistenceContext
  private EntityManager entityManager;

  @Test
  public void testStaticWeavingModelEntity() {
    ModelEntity entity = entityManager.find(ModelEntity.class, 1);
    assertThat("ModelEntity found and woven", entity instanceof PersistenceWeaved);
  }

  @Test
  public void testStaticWeavingModelScoresEntity() {
    ModelScoresEntity entity = entityManager.find(ModelScoresEntity.class, 1);
    assertThat("ModelScoresEntity found and woven", entity instanceof PersistenceWeaved);
  }
}
