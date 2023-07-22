import styled from "styled-components";

const BtnWrap = styled.button`

`;

const BtnRed = (props) => {
  
  return (
    <BtnWrap onClick={() => {
      props.clickEvent();
    }}>{props.name}</BtnWrap>
  )
}

export default BtnRed;