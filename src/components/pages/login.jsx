import {Link} from 'react-router-dom';
import {useState} from 'react';
import LoginService from '../../service/LoginService';
import '../../css/login.css';

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
        const response = await LoginService.postLogin(data);
        if (response.status === 'success') {
            response.data.redirectUrl ? window.location.href = response.redirectUrl : window.location.href = '/'; // redirect 처리

            // const userInfo = {
            //     userId: respose.data.userId
            //     userName: response.data.userName
            // }
            
            // 로그인 이후 세션 처리
            sessionStorage.setItem('isLogin', 'true');
            sessionStorage.setItem('userId', data.userId);
        }
        
    }

    return(
        <section>
            <div className="container">
                <div className="content" id="welcome">
                    <p>술술과 함께 인생 술을 찾아보세요.</p>
                </div>
                <div className="content" id="login-container">
                    <div className="sns-login">
                        <div id="naver-login">네이버로 시작하기</div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Login