package dev.springharvest.search.service;

import dev.springharvest.search.mappers.queries.ISearchMapper;
import dev.springharvest.search.model.entities.EntityMetadata;
import dev.springharvest.search.model.entities.IEntityMetadata;
import dev.springharvest.search.model.queries.requests.filters.BaseFilterBO;
import dev.springharvest.search.model.queries.requests.filters.BaseFilterDTO;
import dev.springharvest.search.model.queries.requests.filters.BaseFilterRequestBO;
import dev.springharvest.search.model.queries.requests.filters.BaseFilterRequestDTO;
import dev.springharvest.search.model.queries.requests.search.SearchRequestDTO;
import dev.springharvest.search.persistence.AbstractCriteriaSearchDao;
import dev.springharvest.search.persistence.ICriteriaSearchRepository;
import dev.springhavest.common.models.entities.BaseEntity;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractSearchService<E extends BaseEntity<K>, K extends Serializable,
    RD extends BaseFilterRequestDTO, RB extends BaseFilterRequestBO, FD extends BaseFilterDTO,
    FB extends BaseFilterBO>
    implements ISearchService<E, K, RD> {

  protected final IEntityMetadata<E> entityMetadata;
  protected ISearchMapper<E, K, RD, RB, FD, FB> filterMapper;
  protected ICriteriaSearchRepository<E, RB> searchRepository;

  protected AbstractSearchService(EntityMetadata<E> entityMetadata,
                                  ISearchMapper<E, K, RD, RB, FD, FB> filterMapper,
                                  AbstractCriteriaSearchDao<E, K, RB> searchRepository) {
    this.entityMetadata = entityMetadata;
    this.filterMapper = filterMapper;
    this.searchRepository = searchRepository;
  }

  protected Class<E> getClazz() {
    ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
    return (Class<E>) paramType.getActualTypeArguments()[1];
  }

  @Override
  public List<E> search(SearchRequestDTO<RD> filterRequest) {
    var searchRequest = filterMapper.toSearchRequest(filterRequest);
    return searchRepository.search(searchRequest);
  }

  @Override
  public Integer count(SearchRequestDTO<RD> filterRequest) {
    return search(filterRequest).size();
  }

  @Override
  public boolean exists(SearchRequestDTO<RD> filterRequest) {
    return count(filterRequest) > 0;
  }

}
