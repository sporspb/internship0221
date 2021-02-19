package ru.philit.ufs.model.entity.oper;

import java.io.Serializable;
import java.util.GregorianCalendar;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class OperationRequest implements Serializable {

  private String operationId;
  private GregorianCalendar createdFrom;
  private GregorianCalendar createdTo;

}
