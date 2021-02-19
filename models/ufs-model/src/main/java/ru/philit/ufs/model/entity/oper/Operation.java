package ru.philit.ufs.model.entity.oper;

import java.math.BigDecimal;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.philit.ufs.model.entity.common.ExternalEntity;
import ru.philit.ufs.model.entity.common.OperationTypeCode;

/**
 * Сущность операции.
 */
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString
@Getter
@Setter
public class Operation extends ExternalEntity {

  private String id;
  private OperationStatus status;
  private OperationTypeCode typeCode;
  private String workplaceId;
  private Date createdDate;
  private Date committedDate;
  private String rollbackReason;
  private String operationId;
  private String workPlaceUId;
  private String operatorId;
  private String cashOrderId;
  private String operationNum;
  private String repId;
  private String senderAccountTypeId;
  private String senderAccountCurrencyType;
  private String senderBank;
  private String senderBankBic;
  private String senderAccountId;
  private BigDecimal amount;
  private String recipientAccountTypeId;
  private String recipientAccountCurrencyType;
  private String recipientBank;
  private String recipientBankBic;
  private String recipientAccountId;
  private String currencyType;

}
