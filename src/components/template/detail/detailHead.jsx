import { useState } from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import CountBtn from "../../atoms/countBtn";
import BtnRed from "../../atoms/btnRed";

const Wrap = styled.div`
  > div {
    display: flex;
  }

  > div:nth-child(1) {
    justify-content: space-between;
  }

  > div:nth-child(2) {
    flex-direction: column;
    > div {
      margin-left: auto;
      display: flex;
    }
    > div: nth-child(1) {
      padding: 15px 0;
      > span {
        margin-right: 15px;
      }
    }
    > div:nth-child(2) {
      width: 60%;

      > button:nth-child(2) {
        width: 100%;
      }
    }
  }
`;
const HeadInfo = styled.div`
  width: 60%;
`;
const Table = styled.table`
  width: 100%;

  > tbody {
    > tr {
      border-top: 1px solid lightgray;
      border-bottom: 1px solid lightgray;

      > td {
        padding: 10px 0;
      }
    }
  }
`;
const BtnCart = styled.button`
  margin-right: 15px;
  border: 1px solid lightgray;
  background-color: white;
  color: lightgray;
  border-radius: 5px;
`;

const DetailHead = (props) => {
  const [ total, setTotal ] = useState(0);
  const prdInfo = props.prdInfo;
  const navigate = useNavigate();

  const doOrder = () => {
    if (total === 0) {
      alert('수량을 선택해주세요.');
      return;
    }
    navigate("/order", { state: { prdNo: [prdInfo.prdNo], total: total + 3000, price: total } });
  }

  const addCart = () => {
    if (total === 0) {
      alert('수량을 선택해주세요.');
      return;
    }

    // 장바구니 저장 로직
  }

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
              <h2>{prdInfo.prdPrice} 원</h2>
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
                      <CountBtn className="cntBtn" cnt={0} setTotal={setTotal} price={prdInfo.prdPrice}/>
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
          <BtnCart onClick={addCart}><i className="icon bi bi-bag"></i></BtnCart>
          <BtnRed  clickEvent={doOrder} name = {'바로 구매하기'}/>
        </div>
      </div>
    </Wrap>
  )
};

export default DetailHead;