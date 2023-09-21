package dev.springharvest.library.domains.books.rest;

import dev.springharvest.crud.rest.AbstractBaseCrudController;
import dev.springharvest.library.domains.books.services.BookService;
import dev.springharvest.shared.domains.books.constants.BookConstants;
import dev.springharvest.shared.domains.books.mappers.IBookMapper;
import dev.springharvest.shared.domains.books.models.dtos.BookDTO;
import dev.springharvest.shared.domains.books.models.entities.BookEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = BookConstants.Controller.TAG)
@RequestMapping(BookConstants.Controller.DOMAIN_CONTEXT)
public class BookControllerImpl extends AbstractBaseCrudController<BookDTO, BookEntity, UUID> {

  protected BookControllerImpl(IBookMapper baseModelMapper, BookService baseService) {
    super(baseModelMapper, baseService);
  }

}
