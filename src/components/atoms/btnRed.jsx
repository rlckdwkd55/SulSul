import styled from "styled-components";

const BtnWrap = styled.button`
  border-radius: 7px;
  border: none;
  background-color: rgb(233, 118, 118);
  color: white;
  height: 40px;
`;

const BtnRed = (props) => {
  
  return (
    <BtnWrap onClick={() => {
      props.clickEvent();
    }}>{props.name}</BtnWrap>
  )
}

export default BtnRed;