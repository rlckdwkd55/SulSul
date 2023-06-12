import { useNavigate } from 'react-router-dom';
import ProductList from '../template/productList'
import styled from 'styled-components';

const MainWrap = styled.div`
    max-width: 1144px;
`;
const BestItemsWrap = styled.div`

`;
const NewItemsWrap = styled.div`

`;


const ContentHead = (props) => {
    const navigate = useNavigate();
    return (
        <div className="wrapper-header">
            <span className="leftItem">{props.title}</span>
            <span className="rightItem" onClick={()=>{
                navigate('/product', {state: {cate: props.cate}});
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
            </BestItemsWrap>
            <NewItemsWrap>
                <ContentHead title={'NEW!'} cate={'new'} />
                <ProductList itemKey={'newItems'} />
            </NewItemsWrap>
        </MainWrap>
    );
}

export default Main