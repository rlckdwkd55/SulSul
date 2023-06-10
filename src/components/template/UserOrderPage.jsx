import React, { useEffect, useState } from "react";
import styled from "styled-components";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import UserService from "../../service/UserService";
import OrderItem from "../atoms/OrderItem";
const OrderWrap = styled.div`
  width: 80%;
  > div:nth-child(1) {
    border-bottom: solid 1px;
    padding-bottom: 16px;
    > h1 {
      font-size: 20px;
    }
  }

  > div {
    > div:nth-child(2) {
      padding-top: 16px;
      border-bottom: solid 1px;
      padding-bottom: 8px;
    }
    > div:nth-child(3) {
      padding-top: 16px;
    }
  }
`;

const UserOrderPage = (props) => {
  const {} = props;
  const [orderList, setOrderList] = useState([]);

  async function postOrderData() {
    const response = await UserService.postOrderData();
    if (response.status === "success") {
      console.log(response);
      setOrderList(response.data);
    } else {
    }
  }

  //orderList
  useEffect(() => {
    postOrderData();
  }, []);
  console.log(orderList);
  return (
    <OrderWrap>
      <div>
        <h1>주문내역</h1>
      </div>
      {orderList.map((item, i) => {
        return <OrderItem item={item} key={i} />;
      })}
    </OrderWrap>
  );
};

export default UserOrderPage;
