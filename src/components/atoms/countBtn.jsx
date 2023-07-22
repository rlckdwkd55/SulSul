import { useState } from "react";
import styled from "styled-components";

const CountWrap = styled.div`
  display: flex;
  
  height: 25px;

  > input[type="number"]::-webkit-outer-spin-button,
    input[type="number"]::-webkit-inner-spin-button {
      -webkit-appearance: none;
      margin: 0;
  }

  > input {
    width: 60px;
  }

  
`;
const CntBtn = styled.button``;

const CountBtn = (props) => {
  const [ count, setCount ] = useState(props.cnt);

  function changeCnt(e) {
    let changeVal = e.target.value;
    setCount(changeVal);
  }

  function changeTotal(count) {
    const originPrice = props.price;
    let totalPrice = originPrice * count;

    props.setTotal(totalPrice);
  }

  return (
    <CountWrap>
      <CntBtn onClick={(e) => {
          if (count === 0) return;
          e.preventDefault();
          setCount(count - 1);
          changeTotal(count -1);
        }}>-</CntBtn>
      <input type="number" min="0" value={count} onChange={(e) => {
                                                    e.preventDefault();
                                                    changeCnt(e);
                                                    changeTotal();
                                                  }}></input>
      <CntBtn onClick={(e) => {
                e.preventDefault();
                setCount(count + 1)
                changeTotal(count + 1);
              }}>+</CntBtn>
    </CountWrap>
  )
}

export default CountBtn;