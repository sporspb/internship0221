package ru.philit.ufs.model.entity.oper;

import javax.xml.datatype.XMLGregorianCalendar;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class OperationRequest {

  private int operationId;
  private XMLGregorianCalendar createdFrom;
  private XMLGregorianCalendar createdTo;

}
