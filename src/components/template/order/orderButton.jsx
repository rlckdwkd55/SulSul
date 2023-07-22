import styled from "styled-components";

const Wrap = styled.div``;
const ButtonWrap = styled.button``;

const OrderButton = (props) => {
  
  function doOrder() {
    if (!props.payment) {
      alert('결제수단을 선택 해주세요.');
    } else if (!props.isAgree){
      alert('약관 동의 체크를 해주세요.');
    } else {
      // 결제 서비스 로직
    }
  }

  return(
    <Wrap>
      <ButtonWrap onClick={() => doOrder()}>{props.total} 원 결제하기</ButtonWrap>
    </Wrap>
  )
}

export default OrderButton;