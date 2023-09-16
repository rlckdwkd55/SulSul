import { useState } from "react";
import styled from "styled-components";
import CountBtn from "../../atoms/countBtn";

const Wrap = styled.div`
  width: 70%;
`;
const BtnWrap = styled.div`
  display: flex;
  padding-bottom: 5px;
  border-bottom: solid 1px dimgray;

  > div {
    margin-left: 20px;
  }

`;
const PrdWrap = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 30px 0;
  border-bottom: solid 1px lightgray;

  > div {
    display: flex;
    justify-content: space-between;
  }

  > div:nth-child(1) {
    width: 20%;
  }

  > div:nth-child(2) {
    width: 70%;

    div:nth-child(1) {
      width: 40%;
    }
    div:nth-child(3) {
      width: 25%;
      text-align: right;
    })
  }
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
      <div>
        <SelectBtnRed onChange={(e) => handleSingleCheck(e.target.checked, data.prdNo)}
                  // 체크된 아이템 배열에 해당 아이템이 있을 경우 선택 활성화, 아닐 시 해제
                  checked={props.checkItems.includes(data.prdNo) ? true : false}/>
        <img src='' alt='' width='100' height='150'/>
      </div>
      <div>
        <div>{data.prdName}</div>
        <CountBtn cnt={orderCnt} setTotal={setOrderPrice} price={data.prdPrice}/>
        <div>{orderPrice} 원</div>
      </div>
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
      {props.cartList && props.cartList > 0 ? (
          props.cartList.map((item) => {
            return <PrdList key={item.prdNo} cartItem={item} checkItems={checkItems} setCheckItems={setCheckItems} setPrice={props.setPrice}/>
          })
        ) : (
          <div style={{margin: '10px 0 0 20px'}}>장바구니에 추가 한 상품이 없습니다.</div>
        )
      }
    </Wrap>
  )
}

export default CartList;