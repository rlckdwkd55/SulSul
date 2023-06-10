import React from "react";
import styled from "styled-components";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
const SideTapWrap = styled.div`
  border: solid 1px #737373;
  border-radius: 8px;
  width: 100%;
  max-width: 180px;
  min-width: 180px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  > div {
    border-bottom: solid 1px #737373;
  }
  > div:nth-child(1) {
    border-radius: 8px 8px 0px 0px;
  }
  > div:nth-last-child(1) {
    border-bottom: none;
    border-radius: 0px 0px 8px 8px;
  }
  &:hover {
    cursor: pointer;
  }
`;

const TabCard = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px;
  > div {
    display: flex;
    align-items: center;
    justify-content: center;
    > p {
      font-weight: 600;
      color: #737373;
    }
    > svg {
      color: #737373;
    }
  }
  //
  &:hover {
    background-color: #f5f5f5;
    > div {
      > p {
        color: red;
      }
      > svg {
        color: red;
      }
    }
  }
`;

const tabList = [
  {
    name: "주문내역",
    value: "order"
  },
  {
    name: "취소/환불 내역",
    value: "refund"
  },
  {
    name: "상품후기",
    value: "review"
  },
  {
    name: "배송지 관리",
    value: "adress"
  },
  {
    name: "회원정보",
    value: "userinfo"
  }
];

const SideTapMenu = (props) => {
  const {} = props;
  return (
    <SideTapWrap>
      {tabList.map((tab, i) => {
        return (
          <TabCard key={i}>
            <div>
              <p>{tab.name}</p>
            </div>
            <div>
              <ArrowForwardIosIcon />
            </div>
          </TabCard>
        );
      })}
    </SideTapWrap>
  );
};

export default SideTapMenu;
