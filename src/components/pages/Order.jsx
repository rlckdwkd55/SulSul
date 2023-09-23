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
import { dateFormat } from "../../js/common";
import { useSelector } from 'react-redux';

const Wrap = styled.div`
  margin: 50px 150px;
`;

const Order = () => {
  const [ orderList, setOrderList ] = useState([]);
  const [ userInfo, setUserInfo ] = useState({});
  const [ userAddr, setUserAddr ] = useState({});
  const [ isAgree, setIsAgree ] = useState(false);
  const [ payment, setPayment ] = useState('');
  const [ orderDetailList, setOrderDetailList ] = useState([])
  const [ jsonData, setJsonData ] = useState({
    "memberEmail": useSelector((state) => state.user.value.userEmail), // email로 변경되어야 할 듯?
    "orderAddress": '',
    "orderReceiver": '',
    "orderRequest": '',
    "orderPhone": '',
    "payMethod": '',
    "orderDetailList": orderDetailList
  })
  const { state } = useLocation();
  const prdList = state.orderInfo.prdList;
  const total = state.orderInfo.total;

  let list = [];
  prdList.forEach(function(data) {
    let orderDetail = {
      "productNo": data.PRODUCT_NO,
      "detailAmount": data.amount,
      "detailPrice": data.totalPrice,
      "payDate": dateFormat(new Date())
    };

    list.push(orderDetail);
  })

  useEffect(()=>{
    const getUserInfo = async (email) => {
      const response = await UserService.postUserInfo(email);

      if (response.status === 'success') {
        setUserInfo({
          "userName": response.data.name,
          "userPhone": response.data.phone,
          "userEmail": response.data.email
        });

        setUserAddr({

        })
      }
    }
    setOrderList(prdList);
    getUserInfo(jsonData.memberEmail);
    setOrderDetailList(list);
  },[])

  return(
    <Wrap>
      <OrderItemList orderList={orderList}/>
      <OrderUserInfo userInfo={userInfo}/>
      <OrderAddr userAddr={userAddr} setJsonData={setJsonData} userName={userInfo.userName}/>
      <OrderPayment price={total} total={total+3000} setPayment={setPayment}/>
      <OrderAgreement setIsAgree={setIsAgree} />
      <OrderButton payment={payment} isAgree={isAgree} total={total} jsonData={jsonData} />
    </Wrap>
  )
}

export default Order;