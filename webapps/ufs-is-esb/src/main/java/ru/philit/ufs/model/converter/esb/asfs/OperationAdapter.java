package ru.philit.ufs.model.converter.esb.asfs;

import java.util.List;
import ru.philit.ufs.model.entity.account.Representative;
import ru.philit.ufs.model.entity.common.OperationTypeCode;
import ru.philit.ufs.model.entity.esb.as_fs.OpStatusType;
import ru.philit.ufs.model.entity.esb.as_fs.OperTypeLabel;
import ru.philit.ufs.model.entity.esb.as_fs.SrvGetOperationRq;
import ru.philit.ufs.model.entity.esb.as_fs.SrvGetOperationRq.SrvGetOperationRqMessage;
import ru.philit.ufs.model.entity.esb.as_fs.SrvGetOperationRs;
import ru.philit.ufs.model.entity.esb.as_fs.SrvGetOperationRs.SrvGetOperationRsMessage.OperationItem;
import ru.philit.ufs.model.entity.esb.pprb.SrvGetRepByCardRs;
import ru.philit.ufs.model.entity.oper.Operation;
import ru.philit.ufs.model.entity.oper.OperationRequest;
import ru.philit.ufs.model.entity.oper.OperationStatus;

/**
 * Преобразователь между сущностью Operation и соответствующим транспортным объектом.
 */
public class OperationAdapter extends AsfsAdapter {

  //******** Converters ********
  private static OperTypeLabel operTypeLabel(OperationTypeCode operationTypeCode) {
    return (operationTypeCode != null) ? OperTypeLabel.fromValue(operationTypeCode.code()) : null;
  }

  private static OperationStatus operationStatus(OpStatusType opStatusType) {
    return (opStatusType != null) ? OperationStatus.getByCode(opStatusType.value()) : null;
  }

  //******** Mappers *******
  private static void map(OperationRequest params, SrvGetOperationRqMessage message) {
    params.setCreatedFrom(message.getCreatedFrom());
    params.setCreatedTo(message.getCreatedTo());
  }

  private static void map(List<OperationItem> operationItem, Operation operation) {
  }

  //******** Methods *******

  /**
   * Возвращает объект запроса операции.
   */
  public static SrvGetOperationRq requestByParams(OperationRequest params) {
    SrvGetOperationRq request = new SrvGetOperationRq();
    request.setHeaderInfo(headerInfo());
    request.setSrvGetOperationRqMessage(new SrvGetOperationRq().getSrvGetOperationRqMessage());
    map(params, request.getSrvGetOperationRqMessage());
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
