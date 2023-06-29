import { useState } from "react";
import styled from "styled-components";

const CountWrap = styled.div`
  display: flex;

  > input[type="number"]::-webkit-outer-spin-button,
    input[type="number"]::-webkit-inner-spin-button {
      -webkit-appearance: none;
      margin: 0;
  }
`;
const CntBtn = styled.button``;

const CountBtn = (props) => {
  const [ count, setCount ] = useState(props.cnt);

  function changeTotal(e) {
    let changeVal = e.target.value;
    setCount(changeVal);
  }

  return (
    <CountWrap>
      <CntBtn onClick={() => {
        if (count === 0) return;
        setCount(current => current - 1)}
        }>-</CntBtn>
      <input type="number" min="0" value={count} onChange={(e) => {changeTotal(e);}}></input>
      <CntBtn onClick={() => {setCount(current => current + 1)}}>+</CntBtn>
    </CountWrap>
  )
}

export default CountBtn;