import { useState } from "react";
import { useLocation } from 'react-router-dom';
import styled from "styled-components";
import DetailHead from "../template/detail/detailHead";

const Wrap = styled.div`

`;
const DetailInfo = styled.div``;
const DetailNav = styled.div``;

const DetailNavBtn = (props) => {
  return (
    <a href={props.url}>{props.btnName}</a>
  )
}

const Detail = () => {
  const { state } = useLocation();
  const { prdNo } = state;

  return (
    <Wrap>
      <DetailHead/>
      <DetailNav>
        <DetailNavBtn btnName={'상품정보'} url={''}/>
        <DetailNavBtn btnName={'후기'} url={''}/>
        <DetailNavBtn btnName={'상품문의'} url={''}/>
      </DetailNav>
      <DetailInfo></DetailInfo>
      {/* <DetailReview></DetailReview>
      <DetailQnA></DetailQnA> */}
    </Wrap>    
  )
}

export default Detail;