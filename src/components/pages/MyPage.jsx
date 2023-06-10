import { useLocation } from "react-router-dom";
import React from "react";
import styled from "styled-components";
import SideTap from "../template/SideTap";
import OrderPage from "../template/OrderPage";

const MyPage = () => {
  return (
    <div>
      <div>
        <SideTap />
      </div>
      <div>
        <OrderPage />
      </div>
    </div>
  );
};

export default MyPage;
