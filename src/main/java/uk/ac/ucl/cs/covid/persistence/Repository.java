package uk.ac.ucl.cs.covid.persistence;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;
import java.util.Optional;

/**
 * Repository of search methods of entities.
 */
@Dependent
public class Repository {

  /**
   * The JPA entity manager.
   */
  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Query to find all scores produced by a model.
   * @param modelId the identifier of the model.
   * @return a {@link List} with the scores. It can be empty.
   */
  public List<ModelScoresEntity> findAllModelScoresForModelId(
    final Integer modelId
  ) {
    final CriteriaQuery<ModelScoresEntity> query =
      new CriteriaQueryBuilder(entityManager)
        .findModelScoresForModelId(modelId);
    return entityManager
      .createQuery(query)
      .getResultList();
  }

  /**
   * Query to find the default model for a given country.
   * @param countryIsoA3 The ISO A3 code for a country.
   * @return An {@link Optional} with the default model.
   */
  public Optional<DefaultModelEntity> findModel(final String countryIsoA3) {
    final CriteriaQuery<DefaultModelEntity> query =
      new CriteriaQueryBuilder(entityManager)
        .findDefaultModelForCountryIsoA3(countryIsoA3);
    final TypedQuery<DefaultModelEntity> typedQuery = entityManager
        .createQuery(query);
    typedQuery.setMaxResults(1);
    return typedQuery.getResultStream().findFirst();
  }

}
