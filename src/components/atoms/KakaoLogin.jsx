import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import LoginService from '../../service/LoginService';
import { useDispatch } from 'react-redux';
import { login } from '../../store/reducers/userSlice';

const KakaoLogin = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const currentUrl = window.location.href;
    // URLSearchParams 객체를 사용하여 쿼리 매개변수를 추출합니다.
    const url = new URL(currentUrl)

    // "code" 매개변수의 값을 가져옵니다.
    const code = url.searchParams.get('code');
    const tokenRequest = async(code) => {
        const response = await LoginService.getTokenRequest('kakao', code);
        if (response.status === 'success') {
            const reslutCode = response.data.reslutCode;
            if (reslutCode === '01') { // 회원 가입 되어있음 로그인 처리
                // 로그인 이후 상태관리
                dispatch(login({
                    isLogin: true,
                    userEmail: response.data.response.email
                }))
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

    return (
        <></>
    )
}

export default KakaoLogin;

