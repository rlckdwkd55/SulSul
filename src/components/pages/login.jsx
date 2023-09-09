import { Link,useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import LoginService from '../../service/LoginService';
import '../../css/login.css';

function Login() {
    const navigate = useNavigate();
    const [id, setId] = useState('');
    const [pwd, setPwd] = useState('');

    async function login(type) {
        // if (id === '' || pwd === '') {
        //     alert('아이디/비밀번호를 입력해 주세요.');
        //     return;
        // }
        // const data = {
        //     userId: id,
        //     userPwd: pwd
        // }
        // const response = await LoginService.postLogin(data);
        // if (response.status === 'success') {
        //     response.data.redirectUrl ? window.location.href = response.redirectUrl : window.location.href = '/'; // redirect 처리

        //     // const userInfo = {
        //     //     userId: respose.data.userId
        //     //     userName: response.data.userName
        //     // }
            
        //     // 로그인 이후 세션 처리
        //     sessionStorage.setItem('isLogin', 'true');
        //     sessionStorage.setItem('userId', data.userId);
        // }

        if (type) {
            const response = await LoginService.getSnsLogin(type);
            if (response.status === 'success') {
                //response.data.redirectUrl ? window.location.href = response.redirectUrl : window.location.href = '/'; // redirect 처리
                window.location.href = response.data;
    
                // const userInfo = {
                //     userId: respose.data.userId
                //     userName: response.data.userName
                // }
                
                // 로그인 이후 세션 처리
                // sessionStorage.setItem('isLogin', 'true');
                // sessionStorage.setItem('userId', data.userId);
            }
        }
    }

    const currentUrl = window.location.href;
     // URLSearchParams 객체를 사용하여 쿼리 매개변수를 추출합니다.
    const url = new URL(currentUrl)
    

     // "code" 매개변수의 값을 가져옵니다.
    const code = url.searchParams.get('code');
    
    const tokenRequest = async(code) => {
        const response = await LoginService.getTokenRequest('naver', code);
            if (response.status === 'success') {
                const reslutCode = response.data.reslutCode;
                if (reslutCode === '01') { // 회원 가입 되어있음 로그인 처리

                } else {
                    if (reslutCode === '02') {  // 미성년자 회원가입 불가
                        alert('성인만 회원가입이 가능합니다.');
                        return;
                    }

                    // 회원가입 처리
                    // 회원가입 화면으로 이동
                    // birthday, birthyear, email, gender, mobile, name
                    const userInfo = response.data.response;
                    navigate('/SNSJoin', { state: {userInfo: userInfo}});
                }

            }
    }

    useEffect(() => {
        if (code) {
            tokenRequest(code);
        }
    }, [])

    return(
        <section>
            <div className="container">
                <div className="content" id="welcome">
                    <p>술술과 함께 인생 술을 찾아보세요.</p>
                </div>
                <div className="content" id="login-container">
                    <div className="sns-login">
                        <div id="naver-login" onClick={() => login('naver')}>네이버로 시작하기</div>
                        <div id="kakao-login" onClick={() => login('kakao')}>카카오로 시작하기</div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Login