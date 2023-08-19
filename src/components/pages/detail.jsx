import { useState, useEffect } from "react";
import { useLocation } from 'react-router-dom';
import styled from "styled-components";
import DetailHead from "../template/detail/detailHead";
import DetailReview from "../template/detail/DetailReview";
import DetailQnA from "../template/detail/DetailQnA";

const Wrap = styled.div`
  margin: 50px 150px;
`;
const DetailInfo = styled.div``;
const DetailNav = styled.div`
  > a {
    margin-right: 10px;
    padding: 5px 10px;
    border: 1px solid red;
    border-radius: 5px;
  }
`;

const DetailNavBtn = (props) => {
  return (
    <a href={props.url}>{props.btnName}</a>
  )
}

const Detail = () => {
  const [ prdInfo, setPrdInfo ] = useState({});
  const [ amount, setAmount ] = useState(0);
  const { state } = useLocation();
  const { prdNo } = state;

  useEffect(() => {
    async function getPrdInfo() {
      // const response = await ProductService.postCartList();
  
      // if (response.status === "success") {
      //   setCartList(response.data);
      // }
    }

    setPrdInfo({
      "prdNo": 1,
      "prdName": '안동소주',
      "prdPrice": 10000,
      "prdAmount": 1,
      "imgPath": '/images/product/product01.jpg'
    })
  }, [])

  return (
    <Wrap>
      <DetailHead prdInfo={prdInfo} amount={amount} setAmount={setAmount}/>
      <DetailNav>
        <DetailNavBtn btnName={'상품정보'} url={''}/>
        <DetailNavBtn btnName={'후기'} url={''}/>
        <DetailNavBtn btnName={'상품문의'} url={''}/>
      </DetailNav>
      <DetailInfo></DetailInfo>
      <DetailReview></DetailReview>
      <DetailQnA></DetailQnA>
    </Wrap>    
  )
}

export default Detail;