import { useState } from "react";
import styled from "styled-components";

const Wrap = styled.div``;
const PaymentWrap = styled.div``;
const PayInfoWrap = styled.div``;
const PaymentMethodBtn = styled.button``;
const PriceAreaWrap = styled.div``;

const PaymentMethod = (props) => {
  const [ paymentCheck, setPaymentCheck ] = useState(false);

  function handleBtnClick(setPayment, methodName) {
    if (paymentCheck) {
      setPayment('');
      setPaymentCheck(false);
    } else {
      if (methodName === '카카오페이') {
        setPayment('KKO');
      }
      setPaymentCheck(true);
    }
  }

  return(
    <PaymentMethodBtn onClick={() => handleBtnClick(props.setPayment, props.methodName)}>
      {props.methodName}
    </PaymentMethodBtn>
  )
}

const PaymentMethodArea = ({setPayment}) => {
  return (
    <PaymentWrap>
      <div>
        <div>결제수단 선택</div>
        <div>
          <PaymentMethod methodName={'카카오페이'} setPayment={setPayment}/>
        </div>
      </div>
      <div>
        <p>품절 환불 안내</p>
        <p>결제하신 수단으로 취소 됩니다.</p>
        <p>
        • 입점업체 배송은 낮은 확률로 상품이 품절일 가능성이 있습니다. 이에 품절 시 빠르게 환불 처리해드립니다.<br/>
        • 현금 환불의 경우, 예금 정보가 일치해야 환불 처리가 가능합니다. 은행명, 계좌번호, 예금주명을 정확히 기재 부탁드립니다.<br/>
        • 환불 받으신 날짜 기준으로 3~5일(주말 제외) 후 결제대행사에서 직접 고객님의 계좌로 환불 처리됩니다.
        </p>
      </div>
    </PaymentWrap>
  )
}

const PriceArea = (props) => {
  const params = props.props;

  return(
    <PriceAreaWrap>
      <div>
        <span>주문금액</span>
        <span>{params.price} 원</span>
      </div>
      <div>
        <span>배송비</span>
        <span>3000원</span>
      </div>
      <div>
        <span>최종결재금액</span>
        <span>{params.total} 원</span>
      </div>
    </PriceAreaWrap>
  )
}

const PayInfo = (props) => {
  return(
    <PayInfoWrap>
      <div><h2>결제 금액</h2></div>
      <PriceArea props={props.props}/>
    </PayInfoWrap>
  )
}

const OrderPayment = (props) => {
  return(
    <Wrap>
      <div><h2>결제 정보</h2></div>
      <div>
        <PaymentMethodArea setPayment={props.setPayment}/>
        <PayInfo props={props}/>
      </div>
    </Wrap>
  )
}

export default OrderPayment;