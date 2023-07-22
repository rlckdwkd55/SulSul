import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import ProductList from '../template/productList';
import BestItemSlider from '../template/BestItemSlider';

const MainWrap = styled.div`
    // padding: 0 70px;
`;
const BestItemsWrap = styled.div`
    margin: 0 80px;
`;
const NewItemsWrap = styled.div`
    margin: 0 80px;
    maxw-width: 1144px;
`;


const ContentHead = (props) => {
    const navigate = useNavigate();
    return (
        <div className="wrapper-header">
            <span className="leftItem">{props.title}</span>
            <span className="rightItem" onClick={()=>{
                navigate('/list', {state: {cate: props.cate}});
            }}>더보기</span>
            <i className="fa-solid fa-chevron-right wrapper-header rightItem"></i>
        </div>
    )
}

const Main = () => {
    return(
        <MainWrap>
            <div id="banner">

            </div>
            <BestItemsWrap>
                <ContentHead title={'BEST PICK!'} cate={'best'} />
                <BestItemSlider/>
            </BestItemsWrap>
            <NewItemsWrap>
                <ContentHead title={'NEW!'} cate={'new'} />
                <ProductList itemKey={'newItems'} />
            </NewItemsWrap>
        </MainWrap>
    );
}

export default Main