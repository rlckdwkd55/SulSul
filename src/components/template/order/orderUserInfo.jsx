import styled from "styled-components";

const Wrap = styled.div``;
const Table = styled.table``;

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