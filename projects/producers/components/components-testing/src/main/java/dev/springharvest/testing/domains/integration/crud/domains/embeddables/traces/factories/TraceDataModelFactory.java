package dev.springharvest.testing.domains.integration.crud.domains.embeddables.traces.factories;

import dev.springharvest.shared.domains.embeddables.traces.dates.models.dtos.TraceDatesDTO;
import dev.springharvest.shared.domains.embeddables.traces.trace.models.dtos.TraceDataDTO;
import dev.springharvest.testing.domains.integration.shared.domains.base.factories.IDomainModelFactory;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TraceDataModelFactory implements IDomainModelFactory<TraceDataDTO> {

  @Override
  public TraceDataDTO buildValidDto() {
    final LocalDate utcTimeStamp = LocalDate.ofInstant(Instant.now(), ZoneOffset.UTC);
    return TraceDataDTO.builder()
        .traceDates(TraceDatesDTO.builder()
                        .dateCreated(utcTimeStamp)
                        .dateUpdated(utcTimeStamp)
                        .build()
                   )
        .build();
  }

  @Override
  public TraceDataDTO buildValidUpdatedDto(TraceDataDTO dto) {
    final LocalDate utcTimeStamp = LocalDate.ofInstant(Instant.now(), ZoneOffset.UTC);
    TraceDatesDTO traceDates = dto.getTraceDates();
    traceDates.setDateUpdated(utcTimeStamp);
    dto.setTraceDates(traceDates);
    return dto;
  }

  @Override
  public TraceDataDTO buildInvalidDto() {
    return TraceDataDTO.builder().build();
  }
}
