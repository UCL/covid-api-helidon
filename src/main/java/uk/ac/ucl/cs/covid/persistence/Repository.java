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

  /**
   * Query to find the number of cases per 100K population per country.
   * @return a {@link List} with the number of cases. It cannot be empty.
   */
  public List<CountryTotalsEntity> findNumberCases100k() {
    final CriteriaQuery<CountryTotalsEntity> query =
        new CriteriaQueryBuilder(entityManager)
          .findCountryTotals();
    final TypedQuery<CountryTotalsEntity> typedQuery = entityManager
        .createQuery(query);
    return typedQuery.getResultList();
  }

  /**
   * Query to find the country by its ISO A3 code.
   * @param countryIsoA3 The ISO A3 code for a country.
   * @return An {@link Optional} with the country.
   */
  public Optional<CountryEntity> findCountry(final String countryIsoA3) {
    final CriteriaQuery<CountryEntity> query =
      new CriteriaQueryBuilder(entityManager)
        .findCountryForCountryIsoA3(countryIsoA3);
    final TypedQuery<CountryEntity> typedQuery = entityManager
        .createQuery(query);
    typedQuery.setMaxResults(1);
    return typedQuery.getResultStream().findFirst();
  }

}
