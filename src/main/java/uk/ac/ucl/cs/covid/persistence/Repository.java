package uk.ac.ucl.cs.covid.persistence;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;

@Dependent
public class Repository {

  @PersistenceContext
  private EntityManager entityManager;

  public List<ModelScoresEntity> findAllModelScoresForModelId(
    final Integer modelId
  ) {
    final CriteriaQuery<ModelScoresEntity> query =
      new CriteriaQueryBuilder(entityManager)
        .findModelScoresForId(modelId);
    final List<ModelScoresEntity> result = entityManager
      .createQuery(query)
      .getResultList();
    return result;
  }

}
