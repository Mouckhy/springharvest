package dev.springharvest.library.domains.books.models.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.springharvest.library.domains.authors.models.dtos.AuthorDTO;
import dev.springharvest.library.domains.publishers.models.dtos.PublisherDTO;
import dev.springhavest.common.models.dtos.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "BookDTO", description = "A book.")
public class BookDTO extends BaseDTO<UUID> {

    private String title;

    private AuthorDTO author;

    private PublisherDTO publisher;

    @Schema(name = "id", description = "The id of the book.", example = "00000000-0000-0000-0000-000000000001")
    public UUID getId() {
        return id;
    }

}