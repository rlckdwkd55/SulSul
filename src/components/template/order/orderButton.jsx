import styled from "styled-components";

const Wrap = styled.div`
  text-align: center;
`;
const ButtonWrap = styled.button`
  border-radius: 7px;
  border: none;
  background-color: rgb(233, 118, 118);
  color: white;
  height: 50px;
  width: 300px;
  font-weight: bold;
`;

const OrderButton = (props) => {
  const jsonData = props.jsonData;

  function doOrder() {
    if (!props.payment) {
      alert('결제수단을 선택 해주세요.');
    } else if (!props.isAgree){
      alert('약관 동의 체크를 해주세요.');
    } else if (!jsonData.orderReceiver && !jsonData.orderAddress){
      alert('배송 정보를 입력 해주세요.')
    } else {
      // 결제 서비스 로직
    }
  }

  return(
    <Wrap>
      <ButtonWrap onClick={() => doOrder()}>{props.total}원 결제하기</ButtonWrap>
    </Wrap>
  )
}

export default OrderButton;