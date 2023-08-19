import styled from "styled-components";

const Wrap = styled.div`
  margin: 60px 0;
  > div:nth-child(1) {
    padding-bottom: 15px;
    border-bottom: solid 1px dimgray;
  }
`;
const Table = styled.table`
  width: 100%;
  padding-bottom: 15px;
  border-bottom: solid 1px lightgray;

  > tr {
    height: 40px;
    > td:nth-child(1) {
        width: 200px;
    }
  }
`;
const ReqSelect = styled.select``;

const OrderAddr = (props) => {
  const userAddr = props.userAddr;
  const addrContents = '';
  

  return(
    <Wrap>
      <div><h2>배송 정보</h2></div>
      <Table>
        <tr>
          <td>배송지</td>
          <td>
            <div>
              <label><input type='radio' name='address' value='A' checked/>기본 배송지</label>
              <label><input type='radio' name='address' value='B'/>새 배송지</label>
            </div>
            {addrContents}
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