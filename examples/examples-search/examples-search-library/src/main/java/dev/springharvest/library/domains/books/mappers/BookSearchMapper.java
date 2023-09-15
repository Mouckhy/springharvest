package dev.springharvest.library.domains.books.mappers;


import dev.springharvest.library.domains.books.models.entities.BookEntityMetadata;
import dev.springharvest.library.domains.books.models.queries.BookFilterBO;
import dev.springharvest.library.domains.books.models.queries.BookFilterDTO;
import dev.springharvest.library.domains.books.models.queries.BookFilterRequestBO;
import dev.springharvest.library.domains.books.models.queries.BookFilterRequestDTO;
import dev.springharvest.library.global.GlobalClazzResolver;
import dev.springharvest.search.mapper.queries.ISearchMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class BookSearchMapper
        implements ISearchMapper<BookFilterRequestDTO, BookFilterRequestBO, BookFilterDTO, BookFilterBO> {

    @Autowired
    private GlobalClazzResolver globalClazzResolver;

    @Autowired
    private BookEntityMetadata entityMetadata;


    public Class<?> getClazz(String path) {
        return globalClazzResolver.getClazz(path);
    }

    public Set<String> getRoots() {
        return entityMetadata.getRootPaths();
    }

}