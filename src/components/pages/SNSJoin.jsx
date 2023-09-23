import {useState, useEffect} from 'react';
import {Link} from 'react-router-dom';
import { useLocation } from 'react-router-dom';
import DaumPostPopup from '../module/daumPost';
import LoginService from '../../service/LoginService';
import * as common from '../../js/common';
import '../../css/join.css';
import styled from 'styled-components';
import { rootColor } from '../../Util/GlobalStyle';
import BtnRed from '../atoms/btnRed';

const Wrap = styled.div`
    margin: 0 25%;
`;
const InputWrap = styled.div`
    margin: 0 25%;
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

const InputArea = (props) => {
    return (
        <InputWrap>
            <table>
                <tr>
                    <td style={{width:'100px'}}>{props.name}</td>    
                    <td><Input value={props.value} id={props.id} onChange={e=>{props.changeInput(e)}}/></td>
                </tr>        
            </table>
        </InputWrap>
    )
}

const SNSJoin = () => {
    const [input, setInput] = useState({
        email: '',
        name: '',
        gender: '',
        address: {
            address: '',
            detailAddress: '',
            postNo: '',
        },
        birthDay: '',
        mobile: ''
    });
    const {state} = useLocation();
    const userInfo = state.userInfo

    useEffect(() => {
        setInput({
            ...input,
            email: userInfo.email,
            gender: userInfo.gender,
            birthDay: userInfo.birthyear + '-' + userInfo.birthday,
            name: userInfo.name,
            mobile: userInfo.mobile
        })
    }, []);

    // event function
    const changeInput = (e) => {
        const {id, value} = e.target;
        setInput({
            ...input,
            [id]: value
        });
    }

    const changeAddress= (e) => {
        const {id, value} = e.target;
        setInput({
            ...input,
            address: {
                ...input.address,
                [id]: value
            }
        });
    }

    async function doJoin() {
        console.log(JSON.stringify(input))

        const response = await LoginService.postJoin(input);
        if (response.status === 'success') {
            alert('회원가입 완료! 로그인 하실 수 있습니다.');
            <Link to='/'/>
        } else {
            alert('회원가입에 실패했습니다.');
        }
    }

    return(
        <Wrap>
            <InputArea name={'이름'} value={input.name} id={'name'} onChange={changeInput}/>
            <InputArea name={'생년월일'} value={input.birthDay} id={'birthDay'} onChange={changeInput}/>
            <InputArea name={'연락처'} value={input.mobile} id={'mobil'} onChange={changeInput}/>
            <InputArea name={'이메일'} value={input.email} id={'email'} onChange={changeInput}/>
            <div className="input-area" style={{marginTop: '30px'}}>
                <label for="postNo">주소</label>
                <div className="input-field" id="inputPostNo">
                    <Input type="text" className="join-input" id="postNo" name="postNo" onChange={e=>{changeAddress(e)}} value={input.address.postNo}/>
                    <DaumPostPopup setInput={setInput}/>
                </div>
                <Input type="text" className="join-input" id="address" name="address" onChange={e=>{changeAddress(e)}} value={input.address.address}/>
                <Input type="text" className="join-input" id="detailAddress" name="detailAddress" placeholder="상세 주소를 입력해 주세요." onChange={e=>{changeAddress(e)}} value={input.address.detailAddress}/>
            </div>
            <div style={{margin:'0 25%'}}>
                <h3>선택입력</h3>
                <table>
                    <tr>
                        <td style={{width:'100px'}}>성별</td>
                        <td><label><input type='radio' id='gender' name='gender' value='M' checked={input.gender === 'M'} onChange={(e) => changeInput(e)}/>남성</label></td>
                        <td><label><input type='radio' id='gender' name='gender' value='F' checked={input.gender === 'F'} onChange={(e) => changeInput(e)}/>여성</label></td>
                    </tr>
                </table>
            </div>

            <div className="button-area" style={{textAlign: 'center', marginTop: '30px'}}>
                <BtnRed style={{width: '200px'}} type="button" name="회원가입" clickEvent={() => doJoin()}/>
            </div>
        </Wrap>
    );
}

export default SNSJoin