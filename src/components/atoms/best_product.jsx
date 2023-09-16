import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const ProductWrap = styled.div`
  margin: 10px  0 0 20px;
  // margin-bottom: 20px;
  // width: calc(100%/4);
  // display: inline-block;

  > div:nth-child(1) {
    margin-bottom: 10px;
    > img {
      width: 100%
    }
  }
`;

const Best_Product = (props) => {
  const navigate = useNavigate();

  function navigateByPrdNo(prdNo) {
    navigate('/detail', {state: {prdNo: prdNo}});
  };

  return(
    <ProductWrap onClick={() => navigateByPrdNo(props.prdNo)}>
      <div>
        <img src={props.imgPath} alt={props.prdName} height='330'/>
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

export default Best_Product;