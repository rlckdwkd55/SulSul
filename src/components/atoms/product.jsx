import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const ProductWrap = styled.div`
  padding: 10px 20px 0 0;
  // max-width: 400px;
  // max-height: 450px;
  
  // > div:nth-child(1) {
  //   > img {
  //     max-width: 100%;
  //     max-heixht: 100%;
  //     min-width: 100%;
  //     min-height: 100%;
  //   }
  // }
`;

const Product = (props) => {
  const navigate = useNavigate();

  function navigateByPrdNo(prdNo) {
    navigate('/detail', {state: {prdNo: prdNo}});
  };

  return(
    <ProductWrap onClick={() => navigateByPrdNo(props.prdNo)}>
      <div>
        <img src={props.imgPath} alt={props.prdName} width='250' height='300'/>
      </div>
      <div>
        <h3>{props.prdName}</h3>
        <span>{props.prdPrice} 원</span>
      </div>
      <div>
        <span>{props.rating}</span>
        <span>리뷰 {props.reviewCnt}</span>
      </div>
    </ProductWrap>
  )
}

export default Product;