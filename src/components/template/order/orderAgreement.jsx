import { useState } from "react";
import styled from "styled-components";

const Wrap = styled.div``;
const AgreementWrap = styled.div``;
const AgreeCheckWrap = styled.div``;

const Detail = ({detail}) => {
  return(
    <pre>{detail}</pre>
  )
}

const Agreements = (props) => {
  const [ isDetailView, setIsDetailView ] = useState(false);

  const title = {
    'personal': '개인정보 수집 • 이용 및 처리 동의',
    'service': '전자지급 결제대행 서비스 이용약관 동의'
  }
  const details = {
    'personal': '상세 약관1',
    'service': '상세 약관2'
  }
  let contents;

  function viewDetail() {
    if (!isDetailView) {
      contents = <Detail detail={details[props.type]}/>
    } else {
      contents = <></>;
    }

    setIsDetailView(!isDetailView);
  }

  return(
    <AgreementWrap>
      <div>
        <span>{title[props.type]}</span>
        <button onClick={() => viewDetail()}>보기</button>
      </div>
      {contents}
    </AgreementWrap>
  )
}

const AgreeCheck = ({setIsAgree}) => {
  function handleCheck(isChecked, setIsAgree) {
    isChecked ? setIsAgree(true) : setIsAgree(false);
  }

  return (
    <AgreeCheckWrap>
      <div>위 내용을 확인 하였으며 결제에 동의합니다.</div>
      <input type='checkbox' onClick={(e) => handleCheck(e.target.checked, setIsAgree)}></input>
    </AgreeCheckWrap>
  )
}

const OrderAgreement = ({setIsAgree}) => {
  return(
    <Wrap>
      <div><h2>개인정보 수집/제공</h2></div>
      <Agreements type={'personal'}/>
      <Agreements type={'service'}/>
      <AgreeCheck setIsAgree={setIsAgree}/>
    </Wrap>
  )
}

export default OrderAgreement;