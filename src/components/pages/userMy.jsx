import { useLocation } from "react-router-dom";
import React from "react";
import styled from "styled-components";
import SideTapMenu from "../template/SideTapMenu";
import UserOrderPage from "../template/UserOrderPage";

const MyPageWraper = styled.div`
  width: 100%;
  display: flex;
  border: solid 1px;
  > div:nth-child(1) {
  }
  > div:nth-child(2) {
    width: 100%;
  }
`;

const userMy = () => {
  return (
    <MyPageWraper>
      <div>
        <SideTapMenu />
      </div>
      <div>
        <UserOrderPage />
      </div>
    </MyPageWraper>
  );
};

export default userMy;
