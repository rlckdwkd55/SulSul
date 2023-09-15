import { useState, useEffect } from "react";
import { useLocation } from 'react-router-dom';
import styled from "styled-components";
import DetailHead from "../template/detail/detailHead";
import DetailReview from "../template/detail/DetailReview";
import DetailQnA from "../template/detail/DetailQnA";
import ProductService from "../../service/ProductService";

const Wrap = styled.div`
  margin: 50px 150px;
`;
const DetailInfo = styled.div`
  margin-top: 50px;

  > img {
    width: 100%
  }
`;
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
    async function getPrdInfo(data) {
      const response = await ProductService.postProductDetail(data);
  
      if (response.status === "success") {
        setPrdInfo(response.data);
      }
    }

    const data = {
      'productNo': prdNo
    }

    getPrdInfo(data);
  }, [])

  return (
    <Wrap>
      <DetailHead prdInfo={prdInfo} amount={amount} setAmount={setAmount}/>
      <DetailNav>
        <DetailNavBtn btnName={'상품정보'} url={''}/>
        <DetailNavBtn btnName={'후기'} url={''}/>
        <DetailNavBtn btnName={'상품문의'} url={''}/>
      </DetailNav>
      <DetailInfo>
        <img src=''></img>
      </DetailInfo>
      <DetailReview></DetailReview>
      <DetailQnA></DetailQnA>
    </Wrap>    
  )
}

export default Detail;