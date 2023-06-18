import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const ProductWrap = styled.div`
  padding: 10px 20px 0 0;
`;

const Product = (props) => {
  const navigate = useNavigate();

  function navigateByPrdNo(prdNo) {
    navigate('/detail', {state: {prdNo: prdNo}});
  };

  return(
    <ProductWrap onClick={() => navigateByPrdNo(props.key)}>
      <div>
        <img src={props.imgPath} alt={props.prdName} width="230" height="280"/>
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