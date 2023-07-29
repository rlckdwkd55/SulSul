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

const OrderUserInfo = (props) => {
  const userInfo = props.userInfo

  return(
    <Wrap>
      <div><h2>주문자 정보</h2></div>
      <Table>
        <tr>
          <td>보내는 분</td>
          <td>{userInfo.userName}</td>
        </tr>
        <tr>
          <td>휴대폰</td>
          <td>{userInfo.userPhone}</td>
        </tr>
        <tr>
          <td>이메일</td>
          <td>{userInfo.userEmail}</td>
        </tr>
      </Table>
    </Wrap>
  )
}

export default OrderUserInfo;