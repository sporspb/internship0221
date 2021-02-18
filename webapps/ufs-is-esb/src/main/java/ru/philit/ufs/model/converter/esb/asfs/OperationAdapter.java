package ru.philit.ufs.model.converter.esb.asfs;

import java.util.List;
import ru.philit.ufs.model.entity.common.OperationTypeCode;
import ru.philit.ufs.model.entity.esb.asfs.OpStatusType;
import ru.philit.ufs.model.entity.esb.asfs.OperTypeLabel;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateOperationRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvCreateOperationRq.SrvCreateOperationRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetOperationRq;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetOperationRq.SrvGetOperationRqMessage;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetOperationRs;
import ru.philit.ufs.model.entity.esb.asfs.SrvGetOperationRs.SrvGetOperationRsMessage.OperationItem;
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
    request.setSrvGetOperationRqMessage(new SrvGetOperationRqMessage());
    map(params, request.getSrvGetOperationRqMessage());
    return request;
  }

  public static SrvCreateOperationRq requestCreateOperation(Operation operation) {
    SrvCreateOperationRq request = new SrvCreateOperationRq();
    request.setHeaderInfo(headerInfo());
    request.setSrvCreateOperationRqMessage(new SrvCreateOperationRqMessage());
    //map(operation, request.getSrvCreateOperationRqMessage());

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
