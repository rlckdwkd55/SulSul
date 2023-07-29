import styled from "styled-components";

const Wrap = styled.div`
  > div:nth-child(1) {
    padding-bottom: 15px;
    border-bottom: solid 1px dimgray;
  }
`;
const ItemWrap = styled.div`
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

const ItemList = (props) => {
  const item = props.item;

  return(
    <ItemWrap>
      <div><img src={item.imgPath} alt='' width='100' height='150'/></div>
      <div>
        <div>{item.prdName}</div>
        <div>{item.prdAmount} 개</div>
        <div>{item.prdPrice} 원</div>
      </div>
    </ItemWrap>
  )
}

const OrderItemList = (props) => {
  const orderList = props.orderList;

  return(
    <Wrap>
      <div><h2>주문상품</h2></div>
      {orderList.map((item) => {
        return <ItemList key={item.prdNo} item={item}/>
      })}
    </Wrap>
  )
}

export default OrderItemList;