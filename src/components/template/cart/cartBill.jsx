import styled from "styled-components";
import BtnRed from "../../atoms/btnRed";
import { useNavigate } from "react-router-dom";

const Wrap = styled.div`
  width: 25%;
  margin-top: 25px;
  height: 300px;

  > div:nth-child(2) {
    text-align: center;
    margin-top: 20px;

     > button {
      width: 100%;
     }
  }
`;
const BillWrap = styled.div`
  height: 70%;
  padding: 15px;
  border: solid 1px lightgray;
  border-radius: 7px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  > div:nth-child(1) {
    border-bottom: solid 1px lightgray;
    padding-bottom: 7px;
  }
`;
const Table = styled.table`
  width: 100%;
  
  > tr {
    height: 30px;
    > td:nth-child(2) {
      text-align: right;
    }
  }
`;
const Total = styled.div`
border-top: solid 1px lightgray;
padding-top: 7px;

  > span:nth-child(2) {
    float: right;
  }
`;

const CartBill = (props) => {
  const navigate = useNavigate();
  const fee = 3000;
  let totalPrice = 0;

  if (props.price) totalPrice = props.price + fee;

  const doOrder = () => {
    navigate("/order", { state: { prdNo: props.selected, total: totalPrice, price: props.price } });
  }

  return (
    <Wrap>
      <BillWrap>
        <div>
          <h2>계산서</h2>
        </div>
        <div>
          <Table>
            <tr>
              <td>상품금액</td>
              <td>{props.price} 원</td>
            </tr>
            <tr>
              <td>상품할인금액</td>
              <td>0 원</td>
            </tr>
            <tr>
              <td>배송비</td>
              <td>{fee} 원</td>
            </tr>
          </Table>
        </div>
        <Total>
          <span>결제예정금액</span>
          <span>{totalPrice} 원</span>
        </Total>
      </BillWrap>
      <div>
        <BtnRed clickEvent={doOrder} name={'주문하기'}/>
      </div>
    </Wrap>
  )
}

export default CartBill;