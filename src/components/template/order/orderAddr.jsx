import { useState, useEffect } from "react";
import DaumPostPopup from "../../module/daumPost";
import styled from "styled-components";
import { rootColor } from '../../../Util/GlobalStyle';

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
const AddrWrap = styled.div`
  display: flex;
  flex-direction: column;
`;
const Input = styled.input`
  width: 300px;
  height: 40px;
  margin: 3px 15px 3px 0;
  border: 1.5px solid lightgray;
  border-radius: 5px;

  &:focus {
    outline: none;
    border-color: ${rootColor.color}
  }
  &:hover {
    border-color: ${rootColor.color}
  }
`;
const ReqSelect = styled.select``;

const OrderAddr = (props) => {
  const [ checkVal, setCheckVal ] = useState('A');
  const [ addrContents, setAddrContents ] = useState();
  const [ addrInfo, setAddrInfo ] = useState({
    postNo: '',
    addr: '',
    addrDetail: ''
  })
  const [ orderReceiver, setOrderReceiver ] = useState();
  const userAddr = props.userAddr;
  const userName = props.userName;

  useEffect(() => {
    setOrderReceiver(userName);
    setAddrInfo({
      postNo: userAddr.postNo,
      addr: userAddr.addr,
      addrDetail: userAddr.addrDetail
    });
  },[userName]);

  const handleRadioCheck = (e) => {
    const value = e.target.value;
    if (value === 'A') {
      setOrderReceiver(userName);
      setAddrInfo({
        postNo: userAddr.postNo,
        addr: userAddr.addr,
        addrDetail: userAddr.addrDetail
      })
      props.setJsonData((prev) => ({
        ...prev,
        orderReceiver: userName,
        orderAddress: addrInfo.postNo + addrInfo.addr + addrInfo.addrDetail
      }));
    } else {
      setOrderReceiver('');
      setAddrInfo({
        postNo: '',
        addr: '',
        addrDetail: ''
      });
      props.setJsonData((prev) => ({
        ...prev,
        orderReceiver: '',
        orderAddress: ''
      }));
    }
    setCheckVal(value);
  }
  
  const onChangeInfo = (e) => {
    if (e.target.name === 'orderReceiver') {
      props.setJsonData((prev) => ({
        ...prev,
        [e.target.name]: e.target.value
      }));
    } else if (e.target.name === 'orderRequest') {
      props.setJsonData((prev) => ({
        ...prev,
        [e.target.name]: e.target.value
      }));
    } else {
      const address = addrInfo.postNo + addrInfo.addr + addrInfo.addrDetail;
      props.setJsonData((prev) => ({
        ...prev,
        orderAddress: address
      }));
    }
  }

  return(
    <Wrap>
      <div><h2>배송 정보</h2></div>
      <Table>
        <tr>
          <td>배송지</td>
          <td>
            <div>
              <label style={{marginRight: '30px'}}><input type='radio' name='address' value='A' checked={checkVal === 'A'} onChange={(e) => handleRadioCheck(e)}/>기본 배송지</label>
              <label><input type='radio' name='address' value='B' checked={checkVal === 'B'} onChange={(e) => handleRadioCheck(e)}/>새 배송지</label>
            </div>
            <div>
              <Input type="text" name="orderReceiver" onChange={(e) => onChangeInfo(e)} value={orderReceiver} placeholder="배송받으시는 분 성함"/>
            </div>
            <AddrWrap>
              <div>
                <Input type="text" name="postNo" check-result="false" onChange={(e) => onChangeInfo(e)} value={addrInfo.postNo}/>
                <DaumPostPopup setInput={setAddrInfo}/>
              </div>
              <Input type="text" name="address" check-result="false" onChange={(e) => onChangeInfo(e)} value={addrInfo.addr}/>
              <Input type="text" name="detailAddress" placeholder="상세 주소를 입력해 주세요." check-result="false" onChange={(e) => onChangeInfo(e)} value={addrInfo.addrDetail}/>
            </AddrWrap>
          </td>
        </tr>
        <tr>
          <td>상세정보</td>
          <td>
            <ReqSelect name="orderRequest" onChange={(e) => onChangeInfo(e)}>
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