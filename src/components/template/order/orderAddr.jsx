import styled from "styled-components";

const Wrap = styled.div``;
const Table = styled.table``;
const ReqSelect = styled.select``;

const OrderAddr = (props) => {
  const userAddr = props.userAddr;

  return(
    <Wrap>
      <div><h2>배송 정보</h2></div>
      <Table>
        <tr>
          <td>배송지</td>
          <td>
            <div>기본 배송지</div>
            <div>{}</div>
          </td>
        </tr>
        <tr>
          <td>상세정보</td>
          <td>
            <ReqSelect>
              <option>선택안함</option>
              <option>부재 시 문 앞에 놓아주세요.</option>
              <option>부재 시 경비실에 맡겨주세요.</option>
              <option>부재 시 연락주세요.</option>
              <option>직접 입력</option>
            </ReqSelect>
          </td>
        </tr>
      </Table>
    </Wrap>
  )
}

export default OrderAddr;