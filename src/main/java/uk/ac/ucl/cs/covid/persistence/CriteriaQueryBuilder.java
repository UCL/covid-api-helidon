package uk.ac.ucl.cs.covid.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class CriteriaQueryBuilder {

  /**
   * A builder of JPA statements using the Criteria API.
   */
  private final CriteriaBuilder criteriaBuilder;

  /**
   * Construct an instance passing the entity manager instead of injecting it.
   * @param entityManager The JPA entity manager
   */
  CriteriaQueryBuilder(final EntityManager entityManager) {
    this.criteriaBuilder = entityManager.getCriteriaBuilder();
  }

  /**
   * A Criteria API query to find the scores for a given model.
   * @param modelId the identifier of a model.
   * @return the select query to find the scores for a given model.
   */
  public CriteriaQuery<ModelScoresEntity> findModelScoresForModelId(
    final Integer modelId
  ) {
    final CriteriaQuery<ModelScoresEntity> query = criteriaBuilder
      .createQuery(ModelScoresEntity.class);
    final Root<ModelScoresEntity> root = query.from(ModelScoresEntity.class);
    final Join<ModelScoresEntity, ModelEntity> join = root
      .join(ModelScoresEntityMetamodel.model);
    final Predicate predicate = criteriaBuilder.equal(
      join.get(ModelEntityMetamodel.id),
      modelId
    );
    return query.where(predicate);
  }

  /**
   * A Criteria API query to find the default model for a given country.
   * @param countryIsoA3 the ISO A3 code of a country.
   * @return the select query to find the default model.
   */
  public CriteriaQuery<DefaultModelEntity> findDefaultModelForCountryIsoA3(
    final String countryIsoA3
  ) {
    final CriteriaQuery<DefaultModelEntity> query = criteriaBuilder
      .createQuery(DefaultModelEntity.class);
    final Root<DefaultModelEntity> root = query.from(DefaultModelEntity.class);
    final Predicate predicate = criteriaBuilder.equal(
      root.get(DefaultModelEntityMetamodel.countryIsoA3),
      countryIsoA3
    );
    return query.where(predicate);
  }

  /**
   * A Criteria API query to retrieve the country based on its ISO A3 code.
   * @param countryIsoA3 the ISO A3 code of a country.
   * @return the selected country.
   */
  public CriteriaQuery<CountryEntity> findCountryForCountryIsoA3(
      final String countryIsoA3) {
    final CriteriaQuery<CountryEntity> query = criteriaBuilder
        .createQuery(CountryEntity.class);
    final Root<CountryEntity> root = query.from(CountryEntity.class);
    final Predicate predicate = criteriaBuilder.equal(
      root.get(CountryEntityMetamodel.countryIsoA3),
      countryIsoA3
    );
    return query.where(predicate);
  }

  /**
   * A Criteria API query to retrieve the number of COVID cases per 100K
   * population in a country.
   * @return the select query to retrieve the number of cases per country.
   */
  public CriteriaQuery<CountryTotalsEntity> findCountryTotals() {
    final CriteriaQuery<CountryTotalsEntity> query = criteriaBuilder
        .createQuery(CountryTotalsEntity.class);
    query.from(CountryTotalsEntity.class);
    return query;
  }

}
