import styled from "styled-components";

const Wrap = styled.div``;
const ItemWrap = styled.div`
  display: flex;
`;

const ItemList = (props) => {
  const item = props.item;

  return(
    <ItemWrap>
      <img src={item.imgPath} alt='' width='100' height='150'/>
      <span>{item.prdName}</span>
      <span>{item.prdAmount} 개</span>
      <span>{item.prdPrice} 원</span>
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