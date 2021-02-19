package ru.philit.ufs.model.converter.esb.asfs;

import java.util.List;
import ru.philit.ufs.model.entity.account.AccountType;
import ru.philit.ufs.model.entity.common.OperationTypeCode;
import ru.philit.ufs.model.entity.esb.asfs.OpStatusType;
import ru.philit.ufs.model.entity.esb.asfs.OperTypeLabel;
import ru.philit.ufs.model.entity.esb.asfs.SrvCommitOperationRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCommitOperationRq.SrvCommitOperationRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateOperationRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateOperationRq.SrvCreateOperationRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetOperationRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetOperationRq.SrvGetOperationRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetOperationRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetOperationRs.SrvGetOperationRsMessage.OperationItem;
import ru.philit.ufs.model.entity.esb.asfs.SrvRollbackOperationRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvRollbackOperationRq.SrvRollbackOperationRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdOperationRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvUpdOperationRq.SrvUpdOperationRqMessage;
import ru.philit.ufs.model.entity.oper.Operation;
import ru.philit.ufs.model.entity.oper.OperationRequest;
import ru.philit.ufs.model.entity.oper.OperationStatus;

/**
 * Преобразователь между сущностью Operation и соответствующим транспортным объектом.
 */
public class OperationAdapter extends AsfsAdapter {

  //******** Converters ********
  private static OperationTypeCode operationTypeCode(OperTypeLabel operTypeLabel) {
    return (operTypeLabel != null) ? OperationTypeCode.valueOf(operTypeLabel.value()) : null;
  }

  private static OperTypeLabel operTypeLabel(OperationTypeCode operationTypeCode) {
    return (operationTypeCode != null) ? OperTypeLabel.fromValue(operationTypeCode.code()) : null;
  }

  private static OperationStatus operationStatus(OpStatusType opStatusType) {
    return (opStatusType != null) ? OperationStatus.getByCode(opStatusType.value()) : null;
  }

  private static OpStatusType opStatusType(OperationStatus operationStatus) {
    return (operationStatus != null) ? OpStatusType.fromValue(operationStatus.code()) : null;
  }

  private static AccountType accountType(String accountTypeId) {
    return (accountTypeId != null) ? AccountType.getByCode(accountTypeId) : null;
  }


  //******** Mappers *******
  private static void map(OperationRequest params, SrvGetOperationRqMessage message) {
    params.setOperationId(message.getOperationId());
    params.setCreatedFrom(message.getCreatedFrom().toGregorianCalendar());
    params.setCreatedTo(message.getCreatedTo().toGregorianCalendar());
  }

  private static void map(Operation operation, SrvUpdOperationRqMessage message) {
    message.setOperationId(operation.getOperationId());
    message.setCashOrderId(operation.getCashOrderId());
    message.setOperationNum(operation.getOperationNum());
    message.setOperationStatus(opStatusType(operation.getStatus()));
    message.setOperationType(operTypeLabel(operation.getTypeCode()));
    message.setWorkPlaceUId(operation.getWorkPlaceUId());
    message.setOperatorId(operation.getOperatorId());
    message.setRepId(operation.getRepId());
    message.setSenderAccountTypeId(operation.getSenderAccountTypeId());
    message.setSenderAccountCurrencyType(operation.getSenderAccountCurrencyType());
    message.setSenderBank(operation.getSenderBank());
    message.setSenderBankBIC(operation.getSenderBankBic());
    message.setSenderAccountId(operation.getSenderAccountId());
    message.setAmount(operation.getAmount());
    message.setRecipientAccountTypeId(operation.getRecipientAccountTypeId());
    message.setRecipientAccountCurrencyType(operation.getRecipientAccountCurrencyType());
    message.setRecipientBank(operation.getRecipientBank());
    message.setRecipientBankBIC(operation.getRecipientBankBic());
    message.setRecipientAccountId(operation.getRecipientAccountId());
    message.setCurrencyType(operation.getCurrencyType());
  }

  private static void map(List<OperationItem> operationItem, Operation operation) {
  }

  private static void map(Operation operation,
      SrvCreateOperationRqMessage srvCreateOperationRqMessage) {

  }

  //******** Methods *******

  /**
   * Возвращает объект запроса операции.
   */
  public static SrvGetOperationRq requestByParams(OperationRequest params) {
    SrvGetOperationRq request = new SrvGetOperationRq();
    request.setHeaderInfo(headerInfo());
    request.setSrvGetOperationRqMessage(new SrvGetOperationRqMessage());
    map(params, request.getSrvGetOperationRqMessage());
    return request;
  }

  /**
   * Возвращает объект запроса на резервирование номера кассовой операции.
   */
  public static SrvCreateOperationRq requestCreateOperation(Operation operation) {
    SrvCreateOperationRq request = new SrvCreateOperationRq();
    request.setHeaderInfo(headerInfo());
    request.setSrvCreateOperationRqMessage(new SrvCreateOperationRqMessage());
    request.getSrvCreateOperationRqMessage()
        .setOperationType(operTypeLabel(operation.getTypeCode()));
    request.getSrvCreateOperationRqMessage().setOperatorId(operation.getOperatorId());
    request.getSrvCreateOperationRqMessage().setWorkPlaceUId(operation.getWorkPlaceUId());
    return request;
  }

  /**
   * Возвращает объект запроса на подтверждение операции по id.
   */
  public static SrvCommitOperationRq requestCommitOperation(String operationId) {
    final SrvCommitOperationRq request = new SrvCommitOperationRq();
    request.setHeaderInfo(headerInfo());
    request.setSrvCommitOperationRqMessage(new SrvCommitOperationRqMessage());
    request.getSrvCommitOperationRqMessage().setOperationId(operationId);
    return request;
  }

  /**
   * Возвращает объект запроса на сохранение атрибутов кассовой операции.
   */
  public static SrvUpdOperationRq requestUpdatedOperation(Operation operation) {
    SrvUpdOperationRq request = new SrvUpdOperationRq();
    request.setHeaderInfo(headerInfo());
    request.setSrvUpdOperationRqMessage(new SrvUpdOperationRqMessage());
    map(operation, request.getSrvUpdOperationRqMessage());
    return request;
  }

  /**
   * Возвращает объект запроса на сторнирование операции по id.
   */
  public static SrvRollbackOperationRq requestRollbackOperation(Operation operation) {
    final SrvRollbackOperationRq request = new SrvRollbackOperationRq();
    request.setHeaderInfo(headerInfo());
    request.setSrvRollbackOperationRqMessage(new SrvRollbackOperationRqMessage());
    request.getSrvRollbackOperationRqMessage().setOperationId(operation.getId());
    request.getSrvRollbackOperationRqMessage().setRollbackReason(operation.getRollbackReason());
    return request;
  }

  /**
   * Преобразует транспортный объект операция во внутреннюю сущность.
   */
  public static Operation convert(SrvGetOperationRs response) {
    Operation operation = new Operation();
    map(response.getHeaderInfo(), operation);
    map(response.getSrvGetOperationRsMessage().getOperationItem(), operation);
    return operation;
  }


}
