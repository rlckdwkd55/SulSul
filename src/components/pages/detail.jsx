import { useState, useEffect } from "react";
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
  const [ prdInfo, setPrdInfo ] = useState({});
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
      <DetailHead prdInfo={prdInfo}/>
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