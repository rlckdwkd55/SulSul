import { useState } from "react";
import styled from "styled-components";
import CountBtn from "../../atoms/countBtn";
import BtnRed from "../../atoms/btnRed";

const Wrap = styled.div`
  > div {
    display: flex;
  }

  > div:nth-child(2) {
    > div {
      display: flex;
    }
  }
`;
const HeadInfo = styled.div``;
const Table = styled.table``;
const BtnCart = styled.button``;

const DetailHead = (props) => {
  const [ total, setTotal ] = useState(0);
  const prdInfo = props.prdInfo;

  return (
    <Wrap>
      <div>
        <img src={prdInfo.imgPath} alt={prdInfo.prdName} width='250' height='300'/>
        <HeadInfo>
          <div>
            <h2>{prdInfo.prdName}</h2> 
            <h3>{prdInfo.prdText}</h3>
            <span>별 {prdInfo.rank}</span>
            <span>리뷰 {prdInfo.review}</span>
            <div>
              <h2>{prdInfo.price} 원</h2>
            </div>
          </div>
          <div>
            <Table>
              <tbody>
                <tr>
                  <td>배송</td>
                  <td>
                    (전국택배) 3,000원 <br/>
                    (제주도 및 도서산간) 3,000원
                  </td>
                </tr>
                <tr>
                  <td>주종</td>
                  <td>일반증류주(진)</td>
                </tr>
                <tr>
                  <td>도수</td>
                  <td>44%</td>
                </tr>
                <tr>
                  <td>용량</td>
                  <td>525ml</td>
                </tr>
                <tr>
                  <td>상품 선택</td>
                  <td>
                    <div>
                      <span>{prdInfo.prdName}</span>
                      <CountBtn cnt={0} setTotal={setTotal} price={prdInfo.price}/>
                    </div>
                  </td>
                </tr>
              </tbody>
            </Table>
          </div>
        </HeadInfo>
      </div>
      <div>
        <div>
          <span>총 상품 금액</span>
          <h2>{total} 원</h2>
        </div>
        <div>
          <BtnCart><i className="icon bi bi-bag"></i></BtnCart>
          <BtnRed name = {'바로 구매하기'}/>
        </div>
      </div>
    </Wrap>
  )
};

export default DetailHead;