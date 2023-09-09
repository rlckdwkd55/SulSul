import {useState, useEffect} from 'react';
import {Link} from 'react-router-dom';
import { useLocation } from 'react-router-dom';
import DaumPostPopup from '../module/daumPost';
import LoginService from '../../service/LoginService';
import * as common from '../../js/common';
import '../../css/join.css';
import styled from 'styled-components';

const Wrap = styled.div`
    margin: 0 25%;
`;
const InputWrap = styled.div`
    margin: 0 25%;
`;

const JoinBtn = (props) => {
    return (
        <button type={props.type} className="btn-join" name={props.name} onClick={() => props.onClickBtn()}>{props.text}</button>
    )
}
const InputArea = (props) => {
    return (
        <InputWrap>
            <table>
                <tr>
                    <td style={{width:'100px'}}>{props.name}</td>    
                    <td>{props.value}</td>
                </tr>        
            </table>
        </InputWrap>
    )
}

const SNSJoin = () => {
    const [input, setInput] = useState({
        userEmail: '',
        userName: '',
        postNo: '',
        address: '',
        detailAddress: '',
        gender: '',
        birthDate: '',
        phone: ''
    });
    const {state} = useLocation();
    const userInfo = state.userInfo

    useEffect(() => {
        setInput({
            ...input,
            userEmail: userInfo.email,
            gender: userInfo.gender,
            birthDate: userInfo.birthyear + '-' + userInfo.birthday,
            userName: userInfo.name,
            phone: userInfo.mobile
        })
    }, []);

    // event function
    function changeInput(e) {
        const {id, value} = e.target;
        setInput({
            ...input,
            [id]: value
        });
    }

    async function doJoin() {

        const response = await LoginService.postJoin(input);
        if (response.status === 'success') {
            <Link to='/'/>
        } else {
            alert('회원가입에 실패했습니다.');
        }
    }

    return(
        <Wrap>
            <InputArea name={'이름'} value={input.userName}/>
            <InputArea name={'생년월일'} value={input.birthDate}/>
            <InputArea name={'연락처'} value={input.phone}/>
            <InputArea name={'이메일'} value={input.userEmail}/>
            <div className="input-area">
                <label for="postNo">주소</label>
                <div className="input-field" id="inputPostNo">
                    <input type="text" className="join-input" id="postNo" name="postNo" onChange={e=>{changeInput(e)}} value={input.postNo}/>
                    <DaumPostPopup setInput={setInput}/>
                </div>
                <input type="text" className="join-input" id="address" name="address" onChange={e=>{changeInput(e)}} value={input.address}/>
                <input type="text" className="join-input" id="detailAddress" name="detailAddress" placeholder="상세 주소를 입력해 주세요." onChange={e=>{changeInput(e)}} value={input.detailAddress}/>
            </div>
            <div style={{margin:'0 25%'}}>
                <h3>선택입력</h3>
                <span>성별</span>
                <span>
                    <label><input type='radio' name='gender' value='M' checked={input.gender === 'M'} />남성</label>
                    <label><input type='radio' name='gender' value='F' checked={input.gender === 'F'} />여성</label>
                </span>
            </div>

            <div className="button-area">
                <JoinBtn type="button" name="joinBtn" text="회원가입" onClickBtn={() => doJoin()}/>
            </div>
        </Wrap>
    );
}

export default SNSJoin