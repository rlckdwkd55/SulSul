import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

const Wrap = styled.div`
    background-color: #f1f1f1;
    margin-top: 60px;
}
`;

const Contents = styled.div`
    height: 200px;
    margin: 30px 50px;

    > div {
        margin-bottom: 15px;
    }
`;

function Footer() {
    const navigate = useNavigate();
    
    const navigatePage = (type) => {
        if (type === 'policy') {
            navigate('/terms/policy');
        } else if (type === 'privacy') {
            navigate('/terms/privacy');
        }
    }

    return(
        <Wrap>
            <Contents>
                <div style={{fontSize: '15pt', fontWeight: 'bold'}}>(주) 술파는사람들</div>
                <div>
                    <div>고객센터: 000-0000-0000</div>
                    <div>평일 10:00 - 18:00, 주말휴무</div>
                </div>
                <div>
                    <span style={{fontWeight: 'bold', marginRight: '15px'}} onClick={() => navigatePage('policy')}>이용약관</span>
                    <span style={{fontWeight: 'bold', marginRight: '15px'}} onClick={() => navigatePage('privacy')}>개인정보처리방침</span>
                </div>
                <div>
                    <span style={{marginRight: '15px'}}>주소: 서울특별시 술술구 술술대로 100,10층</span>
                    <span style={{marginRight: '15px'}}>대표전화: 000-0000-0000</span>
                    <span style={{marginRight: '15px'}}>이메일: help@sulsul.com</span>
                </div>
                <div>
                    술술은 통신판매중개자로서 통신판매 당사자가 아니며, 판매자가 등록한 상품정보 및 거래에 대해 술술은 책임을 지지 않습니다.
                </div>
            </Contents>
        </Wrap>
    );
}

export default Footer