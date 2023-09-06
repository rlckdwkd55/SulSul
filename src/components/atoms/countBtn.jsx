import { useState } from "react";
import styled from "styled-components";

const CountWrap = styled.div`
  display: flex;
  height: 30px;
  border: 1px solid gray;
  border-radius: 5px;
  padding: 3px;
  width: 109px;

  // chrome, safari, edge, ...
  > input[type="number"]::-webkit-outer-spin-button,
    input[type="number"]::-webkit-inner-spin-button {
      -webkit-appearance: none;
      margin: 0;
  }

  // firefox
  > input[type="number"] {
    -moz-appearance: textfield;
  }

  > input {
    width: 60px;
    border: none;
    text-align: center;
  }

  
`;
const CntBtn = styled.button`
  line-height: initial;
  border: none;
  background-color: white;
`;

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
    if (props.setAmount) {
      props.setAmount(count);
    }
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