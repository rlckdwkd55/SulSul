import React, { useEffect, useState } from "react";
import styled from "styled-components";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import UserService from "../../service/UserService";
const OrderWrap = styled.div`
  width: 80%;
  > div:nth-child(1) {
    border-bottom: solid 1px;
    padding-bottom: 16px;
    > h1 {
      font-size: 20px;
    }
  }
  > div:nth-child(2) {
    padding-top: 16px;
    border-bottom: solid 1px;
    padding-bottom: 8px;
  }
  > div:nth-child(3) {
    padding-top: 16px;
  }
`;

const UserOrderPage = (props) => {
  const {} = props;
  const [orderList, setOrderList] = useState([]);

  async function postOrderData() {
    const response = await UserService.postOrderData();
    if (response.status === "success") {
      console.log(response);
      // setOrderList
    } else {
    }
  }

  //orderList
  useEffect(() => {
    postOrderData();
  }, []);

  return (
    <OrderWrap>
      <div>
        <h1>주문내역</h1>
      </div>
      <div>
        <p>날짜</p>
      </div>
      <div>
        <div>
          <img></img>
        </div>
      </div>
    </OrderWrap>
  );
};

export default UserOrderPage;
