import axios from "axios";
import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import UserService from "../../service/UserService";
import OrderService from "../../service/OrderService";
import OrderItemList from "../template/order/orderItemList";
import OrderUserInfo from "../template/order/orderUserInfo";
import OrderAddr from "../template/order/orderAddr";
import OrderPayment from "../template/order/orderPayment";
import OrderAgreement from "../template/order/orderAgreement";
import OrderButton from "../template/order/orderButton";
import styled from "styled-components";

const Wrap = styled.div`
  margin: 50px 150px;
`;

const Order = () => {
  const [ orderList, setOrderList ] = useState([]);
  const [ userInfo, setUserInfo ] = useState({});
  const [ userAddr, setUserAddr ] = useState({});
  const [ isAgree, setIsAgree ] = useState(false);
  const [ payment, setPayment ] = useState('');
  const [ jsonData, setJsonData ] = useState({
    "memberId": sessionStorage.getItem('userId'),
    "orderAddress": '',
    "orderReceiver": '',
    "orderRequest": '',
    "orderPhone": '',
    "payMethod": '',
    "orderDetailList": []
  })
  const { state } = useLocation();
  const prdList = state.orderInfo.prdList;
  const total = state.orderInfo.total;
  const prdNoList = [];

  prdList.forEach(function(data) {
    prdNoList.push(data.prdNo);
  })

  useEffect(()=>{
    setOrderList(prdList);

    setUserInfo({
      "userName": '김용주',
      "userPhone": '010-1111-1111',
      "userEmail": 'test@test.com'
    });

    setUserAddr({

    })
  },[])

  return(
    <Wrap>
      <OrderItemList orderList={orderList}/>
      <OrderUserInfo userInfo={userInfo}/>
      <OrderAddr userAddr={userAddr}/>
      <OrderPayment price={total} total={total+3000} setPayment={setPayment}/>
      <OrderAgreement setIsAgree={setIsAgree} />
      <OrderButton payment={payment} isAgree={isAgree} total={total} prdNoList={prdNoList} />
    </Wrap>
  )
}

export default Order;