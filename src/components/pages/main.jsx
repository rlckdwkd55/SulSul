import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import ProductList from '../template/productList';
import BestItemSlider from '../template/BestItemSlider';

const MainWrap = styled.div`
    max-width: 1144px;
    margin: 0 15%;
`;
const BestItemsWrap = styled.div`

`;
const NewItemsWrap = styled.div`
    // > div {
    //     display: flex;
    //     flex-wrap: wrap;
    //     width: calc(100% + 20px);
    // }
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