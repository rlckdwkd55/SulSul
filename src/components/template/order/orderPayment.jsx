import { useState } from "react";
import styled from "styled-components";

const Wrap = styled.div`
  margin-bottom: 60px;

  > div:nth-child(1) {
    margin-bottom: 15px;
  }
  
  > div:nth-child(2) {
    display: flex;
    justify-content: space-between;
  }
`;
const PaymentWrap = styled.div`
  width: 70%;
  > div {
    display: flex;
    padding-bottom: 10px;
  }

  > div:nth-child(1) {
    padding-top: 10px;
    border-top: solid 1px dimgray;
    height: 70px;
    align-items: center;

    > div:nth-child(1) {
      width: 200px;
    }
  }
  > div:nth-child(2) {
    flex-direction: column;
    border-bottom: solid 1px lightgray;

    > p {
      padding-bottom: 10px;
    }

    > p:nth-child(1) {
      font-weight: bold;
    }
    > p:nth-child(3) {
      font-size: 0.8rem;
    }
  }
`;
const PaymentMethodBtn = styled.button`
  padding: 3px 15px;
  margin-right: 20px;
  border: 1.5px solid lightgray;
  border-radius: 5px;
  background-color: white;
    
  &:hover{
    border-color: rgb(233, 118, 118);
  }
`;
const PayInfoWrap = styled.div`
  width: 30%;
  margin-left: 20px;
`;
const PriceAreaWrap = styled.div`
  margin-top: 20px;
  padding: 25px 20px;
  height: 170px;
  background-color: #f3f3f3;
  border: 1.5px solid lightgray;
  border-radius: 5px;

  display: flex;
  flex-direction: column;
  justify-content: space-around;

  > div {
    display: flex;
  }
  .topArea {
    display: flex;
    flex-direction: column;

    > div {
      padding-bottom: 5px;
      display: flex;
      justify-content: space-between;
    }
  }
  .bottomArea {
    justify-content: space-between;
    padding-top: 10px;
    border-top: 1.5px solid lightgray;

    > span:nth-child(2) {
      color: rgb(233, 118, 118);
      font-weight: bold;
    }
  }
`;

const PaymentMethod = (props) => {

  function handleBtnClick(e) {
    props.setPayment(e.target.name);
    props.setPaymentChecked(e.target.name);
  }

  return(
    <PaymentMethodBtn name={props.method} onClick={(e) => handleBtnClick(e)}>
      {props.methodName}
    </PaymentMethodBtn>
  )
}

const PaymentMethodArea = ({setPayment}) => {
  const [ paymentChecked, setPaymentChecked ] = useState('');

  return (
    <PaymentWrap>
      <div>
        <div>결제수단 선택</div>
        <div>
          <PaymentMethod method={'KKO'} methodName={'카카오페이'} setPayment={setPayment} setPaymentChecked={setPaymentChecked}/>
          <PaymentMethod method={'MU'} methodName={'무통장입금'} setPayment={setPayment} setPaymentChecked={setPaymentChecked}/>
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
      <div className="topArea">
        <div>
          <span>주문금액</span>
          <span>{params.price} 원</span>
        </div>
        <div>
          <span>배송비</span>
          <span>3000 원</span>
        </div>
      </div>
      <div className="bottomArea">
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