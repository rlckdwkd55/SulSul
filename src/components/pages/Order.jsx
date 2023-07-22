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

const Wrap = styled.div``;

const Order = () => {
  const [ orderList, setOrderList ] = useState([]);
  const [ userInfo, setUserInfo ] = useState({});
  const [ userAddr, setUserAddr ] = useState({});
  const [ isAgree, setIsAgree ] = useState(false);
  const [ payment, setPayment ] = useState('');
  const { state } = useLocation();
  const prdNoList = state.prdNo;
  const price = state.price;
  const total = state.total;

  useEffect(()=>{
    setOrderList([
      {
        "prdNo": 1,
        "prdName": '안동소주',
        "prdPrice": 10000,
        "prdAmount": 1,
        "imgPath": '/images/product/product01.jpg'
      },
      {
        "prdNo": 2,
        "prdName": '가평잣막걸리',
        "prdPrice": 20000,
        "prdAmount": 1,
        "imgPath": '/images/product/product01.jpg'
      }
    ]);

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
      <OrderPayment price={price} total={total} setPayment={setPayment}/>
      <OrderAgreement setIsAgree={setIsAgree} />
      <OrderButton payment={payment} isAgree={isAgree} total={total} prdNoList={prdNoList} />
    </Wrap>
  )
}

export default Order;