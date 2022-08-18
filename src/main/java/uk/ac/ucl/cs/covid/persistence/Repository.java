package uk.ac.ucl.cs.covid.persistence;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;
import java.util.Optional;

@Dependent
public class Repository {

  @PersistenceContext
  private EntityManager entityManager;

  public List<ModelScoresEntity> findAllModelScoresForModelId(
    final Integer modelId
  ) {
    final CriteriaQuery<ModelScoresEntity> query =
      new CriteriaQueryBuilder(entityManager)
        .findModelScoresForModelId(modelId);
    final List<ModelScoresEntity> result = entityManager
      .createQuery(query)
      .getResultList();
    return result;
  }

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
