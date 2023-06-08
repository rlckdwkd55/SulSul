import {Link} from 'react-router-dom';
import {useState} from 'react';
import '../../css/login.css';
import axios from 'axios';

function Login() {
    const [id, setId] = useState('');
    const [pwd, setPwd] = useState('');

    async function login() {
        if (id === '' || pwd === '') {
            alert('아이디/비밀번호를 입력해 주세요.');
            return;
        }
        const data = {
            userId: id,
            userPwd: pwd
        }
        try {
            const res = await axios.post('login', data);
            if (res.code === 200) {
                res.redirectUrl ? window.location.href = res.redirectUrl : window.location.href = '/'; // redirect 처리
                // const userInfo = {
                //     userId: res.userId,
                //     userName: res.userName
                // }
                
                // 로그인 이후 세션 처리
                sessionStorage.setItem('isLogin', 'true');
            } else {
                alert(res.msg);
            }
        } catch(e) {
            alert(e);
        }
    }

    return(
        <section>
            <div className="container">
                <div className="content" id="welcome">
                    <p>술술과 함께 인생 술을 찾아보세요.</p>
                </div>
                <div className="content" id="login-container">
                    <div id="login-form">
                        <input type="text" id="userId" name="userId" placeholder="아이디를 입력해 주세요." check-result="false" onChange={(e=>{
                            setId(e.target.value);
                        })}/>
                        <input type="password" id="userPwd" name="userPwd" placeholder="비밀번호를 입력해 주세요." check-result="false" onChange={(e=>{
                            setPwd(e.target.value);
                        })}/>
                        <button className="btn btn-login" id="btn-login" onClick={() => login()}>로그인</button>
                    </div>
                    <Link to='/join'><div className="btn btn-join" id="btn-join">회원가입</div></Link>
                    <div id="find-area">
                        <span className="btn-find" id="btn-findId">아이디 찾기</span>
                        <span className="btn-find" id="btn-findPwd">비밀번호 찾기</span>
                    </div>
                    <div className="sns-login">
                        <div id="naver-login">네이버로 시작하기</div>
                        <div id="kakao-login">카카오로 시작하기</div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Login