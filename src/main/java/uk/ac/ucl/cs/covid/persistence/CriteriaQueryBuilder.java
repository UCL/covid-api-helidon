package uk.ac.ucl.cs.covid.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CriteriaQueryBuilder {

  private final CriteriaBuilder criteriaBuilder;

  /**
   * Construct an instance passing the entity manager instead of injecting it.
   * @param entityManager The JPA entity manager
   */
  CriteriaQueryBuilder(final EntityManager entityManager) {
    this.criteriaBuilder = entityManager.getCriteriaBuilder();
  }

  public CriteriaQuery<ModelScoresEntity> findModelScoresForId(
    final Integer modelId
  ) {
    final CriteriaQuery<ModelScoresEntity> query = criteriaBuilder
      .createQuery(ModelScoresEntity.class);
    final Root<ModelScoresEntity> root = query.from(ModelScoresEntity.class);
    final Predicate predicate = criteriaBuilder.equal(
      root.get(ModelScoresEntityMetamodel.id),
      modelId
    );
    return query.where(predicate);
  }

}
