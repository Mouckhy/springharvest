package dev.springharvest.search.service;

import dev.springharvest.search.mapper.queries.ISearchMapper;
import dev.springharvest.search.model.queries.requests.filters.BaseFilterBO;
import dev.springharvest.search.model.queries.requests.filters.BaseFilterDTO;
import dev.springharvest.search.model.queries.requests.filters.BaseFilterRequestBO;
import dev.springharvest.search.model.queries.requests.filters.BaseFilterRequestDTO;
import dev.springharvest.search.model.queries.requests.search.SearchRequestDTO;
import dev.springharvest.search.persistence.AbstractCriteriaSearchDaoImpl;
import dev.springharvest.search.persistence.ICriteriaSearchRepository;
import dev.springhavest.common.models.entities.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
public abstract class AbstractSearchService<E extends BaseEntity<K>, K extends Serializable,
        RD extends BaseFilterRequestDTO, RB extends BaseFilterRequestBO, FD extends BaseFilterDTO,
        FB extends BaseFilterBO>
        implements ISearchService<E, K, RD> {

    protected ISearchMapper<RD, RB, FD, FB> filterMapper;
    protected ICriteriaSearchRepository<E, RB> criteriaSearchRepository;

    protected AbstractSearchService(ISearchMapper<RD, RB, FD, FB> filterMapper,
                                    AbstractCriteriaSearchDaoImpl<E, K, RB> criteriaSearchRepository) {
        this.filterMapper = filterMapper;
        this.criteriaSearchRepository = criteriaSearchRepository;
    }

    protected Class<E> getClazz() {
        ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) paramType.getActualTypeArguments()[1];
    }

    @Override
    public Optional<E> findByUnique(E entity) {
        return search(SearchRequestDTO.<RD>builder().filters(buildUniqueFilters(entity)).build()).stream().findFirst();
    }

    @Override
    public boolean existsByUnique(E entity) {

        SearchRequestDTO<RD> searchRequestDTO =
                SearchRequestDTO.<RD>builder().filters(buildUniqueFilters(entity)).build();

        return !searchRequestDTO.getFilters().isEmpty() &&
               criteriaSearchRepository.existsByUnique(filterMapper.toSearchRequest(searchRequestDTO));
    }

    @Override
    public List<E> search(SearchRequestDTO<RD> filterRequest) {
        var searchRequest = filterMapper.toSearchRequest(filterRequest);
        return criteriaSearchRepository.search(searchRequest);

    }

    protected abstract Set<RD> buildUniqueFilters(E entity);

}