import { useState } from "react";
import styled from "styled-components";
import CountBtn from "../../atoms/countBtn";

const Wrap = styled.div``;
const BtnWrap = styled.div`
  display: flex;
`;
const PrdWrap = styled.div`
  display: flex;
`;

const SelectBtnRed = (props) => {
  return(
    <>
      <input type="checkbox" onChange={props.onChange} checked={props.checked}></input>
    </>
  )
}

const PrdList = (props) => {
  const [ orderCnt, setOrderCnt ] = useState(0);
  const [ orderPrice, setOrderPrice ] = useState(0);
  const data = props.cartItem;

  // 체크박스 단일 선택
  const handleSingleCheck = (checked, id) => {
    if (checked) {
      // 단일 선택 시 체크된 아이템을 배열에 추가
      props.setCheckItems(prev => [...prev, id]);
      props.setPrice(prev => prev + orderPrice);
    } else {
      // 단일 선택 해제 시 체크된 아이템을 제외한 배열 (필터)
      props.setCheckItems(props.checkItems.filter((el) => el !== id));
      props.setPrice(prev => prev - orderPrice);
    }
  };

  return(
    <PrdWrap>
      <SelectBtnRed onChange={(e) => handleSingleCheck(e.target.checked, data.prdNo)}
                // 체크된 아이템 배열에 해당 아이템이 있을 경우 선택 활성화, 아닐 시 해제
                checked={props.checkItems.includes(data.prdNo) ? true : false}/>
      <img src='' alt='' width='100' height='150'/>
      <span>{data.prdName}</span>
      <CountBtn cnt={orderCnt} setTotal={setOrderPrice} price={data.prdPrice}/>
      <span>{orderPrice} 원</span>
    </PrdWrap>
  )
}

const CartList = (props) => {
  const [ selectCnt, setSelectCnt ] = useState(0);
  
  const checkItems = props.selected;
  const setCheckItems = props.setSelected;

  // 체크박스 전체 선택
  const handleAllCheck = (checked) => {
    if(checked) {
      // 전체 선택 클릭 시 데이터의 모든 아이템(id)를 담은 배열로 checkItems 상태 업데이트
      const idArray = [];
      props.cartList.forEach((el) => idArray.push(el.prdNo));
      setCheckItems(idArray);
    } else {
      // 전체 선택 해제 시 checkItems 를 빈 배열로 상태 업데이트
      setCheckItems([]);
    }
  }

  const deleteCart = () => {}

  return (
    <Wrap>
      <BtnWrap>
        <SelectBtnRed onChange={(e) => handleAllCheck(e.target.checked)}/>
        <div>
          <span>전체선택</span>
          <span>({selectCnt})</span>
        </div>
        <div>
          <span onClick={()=> deleteCart}>선택삭제</span>
        </div>
      </BtnWrap>
      {props.cartList.map((item) => {
        return <PrdList key={item.prdNo} cartItem={item} checkItems={checkItems} setCheckItems={setCheckItems} setPrice={props.setPrice}/>
      })}
    </Wrap>
  )
}

export default CartList;