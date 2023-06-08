import {useState} from 'react';
import axios from 'axios';
import '../../css/join.css';
import * as common from '../../js/common';
import DaumPostPopup from '../module/daumPost';
import {Link} from 'react-router-dom';

const JoinBtn = (props) => {
    return (
        <button type={props.type} className="btn-join" name={props.name} onClick={() => props.onClickBtn()}>{props.text}</button>
    )
}

const Join = () => {
    const [input, setInput] = useState({
        userId: '',
        userPwd: '',
        userPwdCheck: '',
        userEmail: '',
        postNo: '',
        addr: '',
        addrDetail: ''
    });
    
    const [isChecked, setIsChecked] = useState({
        checkId: false,
        checkEmail: false
    });

    // event function
    function changeInput(e) {
        const {id, value} = e.target;
        setInput({
            ...input,
            [id]: value
        });
    }

    function validateInfo(id) {
        let inputVal = input[id];
        let noticeText = common.getNoticeText(id);
        const reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

        if (id === 'userId' && inputVal < 6) {
            alert(noticeText);
            return false;
        } else if (id === 'userPwd' && !reg.test(inputVal)) {
            alert(noticeText);
            return false;
        } else if (id === 'userPwdCheck' && inputVal !== input.userPwd) {
            alert(noticeText);
            return false;
        } else if (inputVal.length === 0 || !inputVal) {
            alert(noticeText);
            return false;
        } else return true;

    }

    async function checkIdEmail(id) {
        let data;
        let url;

        if (id === 'userId') {
            url = '/login/checkId';
            data = {
                userId: input[id]
            }
        } else if (id === 'userEmail') {
            url = '/login/checkEmail';
            data = {
                userEmail: input[id]
            };
        }

        if (validateInfo(id)) {
            try {
                const res = await axios.post(url, data);

                if (res) { //res === true ??
                    setIsChecked({
                        ...isChecked,
                        [id]: true
                    });
                }
            } catch (e) {
                alert(e);
            }
        }

    }

    async function doJoin() {
        for(let key in input) {
            if (!validateInfo(key)) return false;
        }

        try {
            const res = await axios.post('/join', input);

            if (res) <Link to='/'/> //res === true ??
        } catch (e) {
            alert(e);
        }
    }

    return(
        <form id="join-form" method="post" action="/join">
            <div className="input-area">
                <label for="userId">아이디</label>
                <div className="input-field" id="inputId">
                    <input className="join-input" type="text" id="userId" name="userId" placeholder="아이디를 입력해 주세요." check-result="false" onChange={e=>{changeInput(e)}}/>
                    <JoinBtn type="button" name="checkId" text="중복확인" onClickBtn={() => checkIdEmail('userId')}/>
                </div>
            </div>

            <div className="input-area">
                <label for="userPwd">비밀번호</label>
                <input type="password" className="join-input" id="userPwd" name="userPwd" placeholder="비밀번호를 입력해 주세요." check-result="false" onChange={e=>{changeInput(e)}} onBlur={e=>{validateInfo(e.target.id)}}/>
            </div>
            
            <div className="input-area">
                <label for="userPwdCheck">비밀번호 확인</label>
                <input type="password" className="join-input" id="userPwdCheck" name="userPwdCheck" placeholder="비밀번호를 입력해 주세요." check-result="false" onChange={e=>{changeInput(e)}} onBlur={e=>{validateInfo(e.target.id)}}/>
            </div>

            <div className="input-area">
                <label for="userEmail">이메일</label>
                <div className="input-field" id="inputEmail">
                    <input type="text" className="join-input" id="userEmail" name="userEmail" placeholder="이메일을 입력해 주세요." check-result="false" onChange={e=>{changeInput(e)}}/>
                    <JoinBtn type="button" name="checkEmail" text="중복확인" onClickBtn={() => checkIdEmail('userEmail')}/>
                </div>
            </div>

            <div className="input-area">
                <label for="postNo">주소</label>
                <div className="input-field" id="inputPostNo">
                    <input type="text" className="join-input" id="postNo" name="postNo" check-result="false" onChange={e=>{changeInput(e)}} value={input.postNo}/>
                    <DaumPostPopup setInput={setInput}/>
                </div>
                <input type="text" className="join-input" id="addr" name="addr" check-result="false" onChange={e=>{changeInput(e)}} value={input.addr}/>
                <input type="text" className="join-input" id="addrDetail" name="addrDetail" plaveholder="상세 주소를 입력해 주세요." check-result="false" onChange={e=>{changeInput(e)}} value={input.addrDetail}/>
            </div>

            <div className="button-area">
                <JoinBtn type="button" name="joinBtn" text="회원가입" onClickBtn={() => doJoin()}/>
            </div>
        </form>
    );
}

export default Join