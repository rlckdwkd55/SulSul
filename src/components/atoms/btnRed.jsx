import styled from "styled-components";

const BtnWrap = styled.button`

`;

const BtnRed = (props) => {
  return (
    <BtnWrap>{props.name}</BtnWrap>
  )
}

export default BtnRed;