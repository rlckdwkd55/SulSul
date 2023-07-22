import styled from "styled-components";
import BtnRed from "../../atoms/btnRed";
import { useNavigate } from "react-router-dom";

const Wrap = styled.div``;
const BillWrap = styled.div``;
const Table = styled.table``;
const Total = styled.div``;

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
      <BtnRed clickEvent={doOrder} name={'주문하기'}/>
    </Wrap>
  )
}

export default CartBill;