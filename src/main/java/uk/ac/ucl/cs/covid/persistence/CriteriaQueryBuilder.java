package uk.ac.ucl.cs.covid.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

public class CriteriaQueryBuilder {

  private final CriteriaBuilder criteriaBuilder;

  /**
   * Construct an instance passing the entity manager instead of injecting it.
   * @param entityManager The JPA entity manager
   */
  CriteriaQueryBuilder(final EntityManager entityManager) {
    this.criteriaBuilder = entityManager.getCriteriaBuilder();
  }

  public CriteriaQuery<ModelScoresEntity> findModelScoresForModelId(
    final Integer modelId
  ) {
    final CriteriaQuery<ModelScoresEntity> query = criteriaBuilder
      .createQuery(ModelScoresEntity.class);
    final Root<ModelScoresEntity> root = query.from(ModelScoresEntity.class);
    final Join<ModelScoresEntity, ModelEntity> join = root.join(ModelScoresEntityMetamodel.model);
    final Predicate predicate = criteriaBuilder.equal(
      join.get(ModelEntityMetamodel.id),
      modelId
    );
    return query.where(predicate);
  }

}
