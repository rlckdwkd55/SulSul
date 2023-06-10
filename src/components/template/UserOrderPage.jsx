import React from "react";
import styled from "styled-components";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
const OrderWrap = styled.div`
  border: solid 10px;
  width: 100%;
`;

const UserOrderPage = (props) => {
  const {} = props;
  return (
    <OrderWrap>
      <div>
        <h1>주문내역</h1>
      </div>
      <div>
        <p>날짜</p>
      </div>
      <div>상품상세</div>
    </OrderWrap>
  );
};

export default UserOrderPage;
