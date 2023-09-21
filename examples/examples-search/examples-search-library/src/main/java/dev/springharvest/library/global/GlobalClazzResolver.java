package dev.springharvest.library.global;

import dev.springharvest.search.global.IGlobalClazzResolver;
import dev.springharvest.search.model.entities.IEntityMetadata;
import dev.springharvest.shared.domains.authors.models.entities.AuthorEntity;
import dev.springharvest.shared.domains.books.models.entities.BookEntity;
import dev.springharvest.shared.domains.publishers.models.entities.PublisherEntity;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GlobalClazzResolver implements IGlobalClazzResolver {

  private final Map<String, IEntityMetadata<?>> ENTITY_METADATA;

  @Autowired
  public GlobalClazzResolver(IEntityMetadata<AuthorEntity> authorMetadata,
                             IEntityMetadata<PublisherEntity> publisherMetadata,
                             IEntityMetadata<BookEntity> bookMetadata) {

    this.ENTITY_METADATA = new HashMap<>();
    ENTITY_METADATA.put(authorMetadata.getDomainName(), authorMetadata);
    ENTITY_METADATA.put(publisherMetadata.getDomainName(), publisherMetadata);
    ENTITY_METADATA.put(bookMetadata.getDomainName(), bookMetadata);
  }

  @Override
  public Map<String, IEntityMetadata<?>> getEntityMetadataMap() {
    return ENTITY_METADATA;
  }

}
